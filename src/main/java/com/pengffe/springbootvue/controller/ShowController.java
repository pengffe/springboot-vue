package com.pengffe.springbootvue.controller;

import com.pengffe.springbootvue.bean.User;
import com.pengffe.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author pengffe  Email: pengffe@gmail.com
 * @descrption
 * @date
 */

@RestController
//@RequestMapping(value = "/show_users_interested")
public class ShowController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/show_users_interested/{type}/{username}", method = RequestMethod.GET)
    public List<User> getUsers(@PathVariable("type")String type, @PathVariable("username")String username){
        System.out.println("controller: type = [" + type + "], username = [" + username + "]");
        List<User> userList = userService.getUsersBySameType(type, username);
//        for (User item: userList
//             ) {
//            System.out.println(item);
//        }
        return userList;
    }

}
