package com.c1120g1.adweb.common;

public class AuthLogin {

    private String username;
    private String password;

    public AuthLogin() {
    }

    public AuthLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
