package com.example.webshop.bo;

import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserHandler {

    public static boolean authenticateUser(HttpServletRequest request){
        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        User user = User.searchUser(userName);
        if(user==null)
            return false;
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
    public static boolean isUserAdmin(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if(userInfo == null)
            return false;
        User user = User.searchUser(userInfo.getName());
        if(user == null)
            return false;
        return user.getRole().equals(Roles.ADMIN) && user.getToken().equals(userInfo.getToken());
    }
    public static boolean isUserW_Staff(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if(userInfo == null)
            return false;
        User user = User.searchUser(userInfo.getName());
        if(user == null)
            return false;
        return user.getRole().equals(Roles.W_STAFF) && user.getToken().equals(userInfo.getToken());
    }

}
