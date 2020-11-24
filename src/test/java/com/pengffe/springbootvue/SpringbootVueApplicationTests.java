package com.pengffe.springbootvue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import javax.sql.DataSource;


@SpringBootTest
class SpringbootVueApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {

        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }


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
    @Test
    public void TestSendSimpleMail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("379102265@qq.com");
        message.setSubject("Active your account!");
        message.setText("send email for account");
        message.setFrom(from);

        mailSender.send(message);
    }


    @Test
    public void createRandomCode(){
        String val = "";
        System.out.println("val: " + val);
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
        System.out.println("val: " + val);
    }


}
