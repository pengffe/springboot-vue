package com.pengffe.springbootvue.controller;

import com.pengffe.springbootvue.bean.User;
import com.pengffe.springbootvue.service.MailService;
import com.pengffe.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author pengffe  Email: pengffe@gmail.com
 * @descrption
 * @date
 */

@Controller
public class RegistrationController {
    
    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @ResponseBody
    @RequestMapping(value = "/user_register", method = RequestMethod.POST)
    public String Register(@RequestBody User user){
        System.out.println("user = " + user);
            if (!userService.isExist(user.getUsername())){
                System.out.println("用户bu存在");
                userService.addUser(user);
                mailService.createVerifyCode();
                mailService.sendEmail(user.getUsername());
            }else{
                System.out.println("用户已存在");
            }
        return user.toString();
    }
}
