package com.example.webshop.ui;

public class UserInfo {
    private final String name;
    private final String role;
    private final String token;

    public UserInfo(String name, String role, String token) {
        this.name = name;
        this.role = role;
        this.token = token;
    }


    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

}
