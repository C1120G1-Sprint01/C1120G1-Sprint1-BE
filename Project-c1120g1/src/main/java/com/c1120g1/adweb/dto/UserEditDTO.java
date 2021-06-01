package com.c1120g1.adweb.dto;

import com.c1120g1.adweb.entity.Ward;

public class UserEditDTO {
    private Integer userId;
    private String name;
    private String email;
    private String phone;
    private Ward ward;

    public UserEditDTO(Integer userId, String name, String email, String phone, Ward ward) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.ward = ward;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
