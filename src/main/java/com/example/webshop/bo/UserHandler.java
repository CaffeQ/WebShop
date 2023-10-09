package com.example.webshop.bo;

import com.example.webshop.ui.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Collection;

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
        public static final String STAFF = "staff";
    }
    public static boolean createUser(HttpServletRequest request){
        String email = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("Creating user: " + email + " " + password);
        User user = User.searchUser(email);
        if(user != null)
            return false;
        return User.createUser(email,password);
    }

    public static boolean removeUserByUserID(HttpServletRequest request){
        String userEmail = request.getParameter("changeUserStatusByUserName");
        User user = User.searchUser(userEmail);
        return User.removeUserByUserID(user.getId());
    }
    public static boolean activateUserByUserID(HttpServletRequest request){
        String userEmail =  request.getParameter("changeUserStatusByUserName");
        User user = User.searchUser(userEmail);
        return User.activateUserByUserID(user.getId());
    }

    public static boolean changeUserRole(HttpServletRequest request){
        String userEmail =  request.getParameter("changeUserRoleByEmail");
        String role =  request.getParameter("changeUserRoleToRole");
        User user = User.searchUser(userEmail);
        user.setRole(role);
        return User.changeUserRole(user);
    }

    public static Collection<UserInfo> getAllUsers(){
        ArrayList<User> c = User.getAll();
        ArrayList<UserInfo> users = new ArrayList<>();
        for (User user : c) {
            users.add(new UserInfo(
                    user.getEmail(),
                    user.getRole(),
                    user.getToken()
            ));
        }
        return users;
    }
    public static Collection<UserInfo> getAllUsersByStatus(String status){

        boolean boolStatus = false;

        if(status.equals("active")) boolStatus = true;

        ArrayList<User> c = User.getUsersByStatus(boolStatus);
        ArrayList<UserInfo> users = new ArrayList<>();
        for (User user : c) {
            users.add(new UserInfo(
                    user.getEmail(),
                    user.getRole(),
                    user.getToken()
            ));
        }
        return users;
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
        User user = User.searchUser(userInfo.getEmail());
        return user.getRole().equals(userInfo.getRole()) &&
                user.getToken().equals(userInfo.getToken()) &&
                user.isActive();
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
        User user = User.searchUser(userInfo.getEmail());
        if(user == null)
            return false;
        return user.getRole().equals(Roles.ADMIN) && user.getToken().equals(userInfo.getToken()) &&
                user.isActive();
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
        User user = User.searchUser(userInfo.getEmail());
        if(user == null)
            return false;
        return user.getRole().equals(Roles.STAFF) && user.getToken().equals(userInfo.getToken());
    }

}
