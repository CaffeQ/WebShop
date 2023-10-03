package com.example.webshop.bo;

import com.example.webshop.db.UserDB;

public class UserHandler {

    public static boolean authenticateUser(String userName,String password){
        User user = User.searchUser(userName);
        if(user==null)
            return false;
        System.out.println("Authenticating user: "+user.toString());
        return user.getName().equals(userName) &&
                user.getPassword().equals(password);

    }
}
