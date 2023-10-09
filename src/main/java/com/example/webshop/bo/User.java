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

    /**
     * Initializes a new User with the given attributes.
     *
     * @param id Unique identifier for the user.
     * @param email Email address of the user.
     * @param password Password of the user.
     * @param role Role of the user.
     * @param token Token for user authentication.
     */
    protected User(int id, String email, String password, String role, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }


    /**
     * Searches for a User based on their email address.
     *
     * @param email Email address of the user to search for.
     * @return User User object corresponding to the given email address.
     */
    protected static User searchUser(String email){
        return UserDB.searchUser(email);
    }
    protected static ArrayList<User> getAll(){
        return UserDB.getAll();
    }
    protected static ArrayList<User> getUsersByStatus(){
        return UserDB.getUsersByStatus();
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
