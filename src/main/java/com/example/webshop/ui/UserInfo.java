package com.example.webshop.ui;

public class UserInfo {
    private final String email;
    private final String role;
    private final String token;

    public UserInfo(String email, String role, String token) {
        this.email = email;
        this.role = role;
        this.token = token;
    }


    public String getName() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

}
