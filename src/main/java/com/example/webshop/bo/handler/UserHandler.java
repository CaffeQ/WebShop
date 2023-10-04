package com.example.webshop.bo.handler;

import com.example.webshop.bo.User;
import com.example.webshop.db.UserDB;
import com.example.webshop.ui.UserInfo;

public class UserHandler {

    public static boolean authenticateUser(String userName,String password){
        User user = User.searchUser(userName);
        if(user==null)
            return false;
        System.out.println("Authenticating user: "+user.toString());
        return user.getName().equals(userName) &&
                user.getPassword().equals(password);

    }
    public static UserInfo getUser(String name){
        User user = User.searchUser(name);
        if(user == null)
            return null;
        return new UserInfo(user.getName(),user.getRole(),user.getToken());
    }
}
