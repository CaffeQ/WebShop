package com.example.webshop.bo.handler;

import com.example.webshop.bo.User;
import com.example.webshop.db.UserDB;
import com.example.webshop.ui.ItemInfo;
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
    /**
        Kontrollerar anvöndare från vyn i affärssikten genom
        att använda sig av token och jämför om den stämmer,
        sedan kan en operation göras om rollen gäller

     */

    public static UserInfo getUser(String name){
        User user = User.searchUser(name);
        if(user == null)
            return null;
        return new UserInfo(user.getName(),user.getRole(),user.getToken());
    }
    public static void updateUserToken(User user){

    }
}
