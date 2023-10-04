package com.example.webshop.bo;

import com.example.webshop.db.UserDB;

import java.util.UUID;

public class User {
    private final int id;
    private String name;
    private String password;
    private String role;
    private String token;

    public User(int id, String name,
                String password, String role, String token) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public static boolean createUser(User user){
        UserDB.createUser( (UserDB) user);
        return true;
    }

    public static User searchUser(String userName){
        return UserDB.searchUser(userName);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
