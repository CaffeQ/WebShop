package com.example.webshop.bo.handler;

import com.example.webshop.bo.User;
import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserHandler {


    public static boolean authenticateUser(HttpServletRequest request){
        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("User name = " + userName );
        System.out.println("User password = " + password );
        User user = User.searchUser(userName);
        if(user==null)
            return false;
        System.out.println("Authenticating user: "+user.toString());
        if( user.getName().equals(userName) && user.getPassword().equals(password)) {
            UserInfo userInfo = new UserInfo(user.getName(),user.getRole(),user.getToken());
            request.getSession().setAttribute("user",userInfo);
            return true;
        }
        return false;
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
    public static boolean isVerified(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if(userInfo == null)
            return false;
        User user = User.searchUser(userInfo.getName());
        return user.getRole().equals(userInfo.getRole()) &&
                user.getToken().equals(userInfo.getToken());
    }
}
