package com.pengffe.springbootvue.service;

import com.pengffe.springbootvue.bean.User;
import com.pengffe.springbootvue.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengffe  Email: pengffe@gmail.com
 * @descrption
 * @date
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean isExist(String username) {

        System.out.println("username = [" + username + "]");
        System.out.println("开始检测用户是否存在");

        Integer num = userMapper.countByUsername(username);
        if (0 != num){
            return true;
        }else{
            return false;
        }
    }

    public void addUser(User user){
        System.out.println("添加用户");
        userMapper.addUser(user);
    }

    public boolean login(String username, String password){
        if (isExist(username)){
            if (password.equals(userMapper.selectPasswordByUsername(username))){
                return true;
            }
        }
        return false;
    }

    public List<User> getUsersBySameType(String type, String username){
        System.out.println("service: type = [" + type + "], username = [" + username + "]");

        User user = userMapper.selectByUsername(username);
        switch (type){
            case "school":
                System.out.println("type = " + type);
                System.out.println("content = " + user.getSchool());
                return userMapper.selectUserByType(user.getSchool(), username);
            case "enrollTime":
        }
        return null;
    }

    public List<User> showFriendsByUsername(String username){
        return userMapper.selectFriendsByUsername(username);
    }

    public boolean follow(String username, String friendUsername) {
        return userMapper.addFriendToUser(username, friendUsername);
    }

    public boolean unfollow(String username, String friendUsername) {
        return userMapper.removeFriendToUser(username, friendUsername);
    }
}
