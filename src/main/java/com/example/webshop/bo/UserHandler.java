package com.example.webshop.bo;

import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Handles user authentication and role verification in the webshop system.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class UserHandler {
    /**
     * Constants representing user roles in the system.
     */
    private static class Roles {
        public static final String ADMIN = "admin";
        public static final String CUSTOMER = "customer";
        public static final String W_STAFF = "warehouse_staff";
    }


    /**
     * Authenticates a user based on the provided HttpServletRequest parameters.
     *
     * @param request HttpServletRequest containing user's name and password.
     * @return boolean True if authentication is successful, otherwise false.
     */
    public static boolean authenticateUser(HttpServletRequest request){
        String userEmail = request.getParameter("name");
        String password = request.getParameter("password");
        User user = User.searchUser(userEmail);
        if(user==null)
            return false;
        if( user.getEmail().equals(userEmail) && user.getPassword().equals(password)) {
            UserInfo userInfo = new UserInfo(user.getEmail(),user.getRole(),user.getToken());
            request.getSession().setAttribute("user",userInfo);
            return true;
        }
        return false;
    }

    /**
     * Retrieves user information based on their name.
     *
     * @param userEmail The name of the user to search for.
     * @return UserInfo An object containing user information or null if not found.
     */
    public static UserInfo getUserInfo(String userEmail){
        User user = User.searchUser(userEmail);
        if(user == null)
            return null;
        return new UserInfo(user.getEmail(),user.getRole(),user.getToken());
    }

    /**
     * Verifies if the user session is valid.
     *
     * @param session HttpSession object containing user information.
     * @return boolean True if the user is verified, otherwise false.
     */
    public static boolean isVerified(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if(userInfo == null)
            return false;
        User user = User.searchUser(userInfo.getName());
        return user.getRole().equals(userInfo.getRole()) &&
                user.getToken().equals(userInfo.getToken());
    }

    /**
     * Checks if the user is an admin.
     *
     * @param session HttpSession object containing user information.
     * @return boolean True if the user is an admin, otherwise false.
     */
    public static boolean isUserAdmin(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if(userInfo == null)
            return false;
        User user = User.searchUser(userInfo.getName());
        if(user == null)
            return false;
        return user.getRole().equals(Roles.ADMIN) && user.getToken().equals(userInfo.getToken());
    }

    /**
     * Checks if the user is warehouse staff.
     *
     * @param session HttpSession object containing user information.
     * @return boolean True if the user is warehouse staff, otherwise false.
     */
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
