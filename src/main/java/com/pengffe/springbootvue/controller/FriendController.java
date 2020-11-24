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
@RequestMapping(value = "/friends")
public class FriendController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public List<User> showFriends(@PathVariable("username")String username){
        System.out.println("Show friends: username = [" + username + "]");
        List<User> userList = userService.showFriendsByUsername(username);
        return userList;
    }

    @RequestMapping(value = "/{username}/{friendUsername}", method = RequestMethod.POST)
    public boolean follow(@PathVariable("username")String username,
                              @PathVariable("friendUsername")String friendUsername){
        System.out.println("Addfriends: username = [" + username + "], friendUsername = [" + friendUsername + "]");
        return userService.follow(username, friendUsername);

    }

    @RequestMapping(value = "/{username}/{friendUsername}", method = RequestMethod.DELETE)
    public boolean unfollow(@PathVariable("username")String username,
                                 @PathVariable("friendUsername")String friendUsername){
        System.out.println("Removefriends: username = [" + username + "], friendUsername = [" + friendUsername + "]");
        return userService.unfollow(username, friendUsername);
    }
}
