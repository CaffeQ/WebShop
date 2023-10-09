package com.example.webshop.bo;

import com.example.webshop.db.UserDB;

import java.util.ArrayList;


/**
 * Represents a user in the webshop system.
 *
 * @author Tim Johansson
 * @version 1.0
 * @since 2023-10-07
 */
public class User {
    private final int id;
    private String email;
    private String password;
    private String role;
    private String token;
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Initializes a new User with the given attributes.
     *
     * @param id Unique identifier for the user.
     * @param email Email address of the user.
     * @param password Password of the user.
     * @param role Role of the user.
     * @param token Token for user authentication.
     */
    protected User(int id, String email, String password, String role, String token, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
        this.isActive = isActive;
    }


    /**
     * Searches for a User based on their email address.
     *
     * @param email Email address of the user to search for.
     * @return User object corresponding to the given email address.
     */
    protected static boolean createUser(String userName, String password){
        return UserDB.createUser(userName,password);
    }
    protected static User searchUser(String email){
        return UserDB.searchUser(email);
    }

    protected static ArrayList<User> getAll(){
        return UserDB.getAllUsers();
    }
    protected static ArrayList<User> getUsersByStatus(boolean status){
        return UserDB.getUsersByStatus(status);
    }
    protected static boolean removeUserByUserID(int userID){
        return UserDB.removeUserByUserID(userID);
    }
    protected static boolean activateUserByUserID(int userID){
        return UserDB.activateUserByUserID(userID);
    }

    public int getId() {return id;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
