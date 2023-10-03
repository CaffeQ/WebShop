package com.example.webshop.ui;

public class UserInfo {
    private String name;
    private String role;
    private String token;

    public UserInfo(String name, String role, String token) {
        this.name = name;
        this.role = role;
        this.token = token;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
