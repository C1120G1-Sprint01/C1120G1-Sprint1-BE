package com.c1120g1.adweb.dto;

import com.c1120g1.adweb.entity.Ward;

public class UserDTO {

    private String name;
    private String username ;
    private String email;
    private String phone;
    private Ward ward;
    private String avatarUrl;
    private String registerDate ;
    private String password ;
    private String confirmPassword ;

    public UserDTO(String name, String username, String email, String phone, Ward ward, String avatarUrl, String registerDate, String password, String confirmPassword) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.ward = ward;
        this.avatarUrl = avatarUrl;
        this.registerDate = registerDate;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
