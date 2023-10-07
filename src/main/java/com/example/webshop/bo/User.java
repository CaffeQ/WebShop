package com.example.webshop.bo;

import com.example.webshop.db.UserDB;

public class User {
    private final int id;
    private String email;
    private String password;
    private String role;
    private String token;

    protected User(int id, String email, String password, String role, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }


    protected static User searchUser(String email){
        return UserDB.searchUser(email);
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
