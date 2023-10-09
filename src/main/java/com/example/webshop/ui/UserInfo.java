package com.example.webshop.ui;

/**
 * Represents user information for the UI layer.
 * Holds details like the email, role, and token of the user.
 */

public class UserInfo {
    private final String email;
    private final String role;
    private final String token;

    /**
     * Constructs a new UserInfo object to encapsulate user details.
     *
     * @param email The email address of the user.
     * @param role The role of the user (e.g., admin, customer).
     * @param token The authentication token for the user.
     */
    public UserInfo(String email, String role, String token) {
        this.email = email;
        this.role = role;
        this.token = token;
    }


    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

}
