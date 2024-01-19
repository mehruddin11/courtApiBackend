package com.nagarro.ProductSearchApi.dto;

import com.nagarro.ProductSearchApi.model.User;

public class AuthResponse {
    private String token;
    private User user;

    // Constructors, getters, and setters...

    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
