package com.pengffe.springbootvue.controller;

import com.pengffe.springbootvue.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengffe  Email: pengffe@gmail.com
 * @descrption
 * @date
 */

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/{code}/{username}")
    public boolean emailVerify(@PathVariable("code")String code, @PathVariable("code")String username){
        return mailService.emailVerify(username, code);
    }
}
