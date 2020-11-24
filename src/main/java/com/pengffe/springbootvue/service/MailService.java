package com.pengffe.springbootvue.service;

import com.pengffe.springbootvue.mapper.MailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author pengffe  Email: pengffe@gmail.com
 * @descrption
 * @date
 */

@Service
public class MailService {

    @Autowired
    private MailMapper mailMapper;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 简单文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet 邮件内容
     */
    public void sendEmail(String username){

        String code = createRandomCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(username);
        message.setSubject("Activate account!");
        message.setText("Please click the follow link to activate your account:" +
                " \n http://127.0.0.1:8000/user_active/" + code + "/" + username);
        message.setFrom(from);

        mailSender.send(message);
    }

    private String createRandomCode(){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public boolean createVerifyCode(){
        String code = createRandomCode();
        return mailMapper.createVerifyCode(code);
    }

    public boolean emailVerify(String username, String code) {

        String code_copy = mailMapper.emailVerify(username);
        if(code_copy.equals(code)){
            return true;
        }
        return false;
    }
}

