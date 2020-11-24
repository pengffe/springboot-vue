package com.pengffe.springbootvue.controller;

import com.pengffe.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengffe  Email: pengffe@gmail.com
 * @descrption
 * @date 2020-11-15
 */

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user_login/{username}/{password}", method = RequestMethod.GET)
    public Map<String, Object> Register(@PathVariable("username") String username, @PathVariable("password") String password){
        System.out.println("开始登陆：user = " + username + " password = " + password);
        Boolean isSuccess = userService.login(username, password);
        Map<String, Object> map = new HashMap<>();
        if (isSuccess){
            System.out.println("success");
            map.put("login", "true");
        }else{
            System.out.println("fail");
            map.put("login", "false");
        }
        return map;
    }
}
