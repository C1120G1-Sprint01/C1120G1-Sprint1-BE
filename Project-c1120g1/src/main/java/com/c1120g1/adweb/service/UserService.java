package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    User findById(Integer id);

    void delete(Integer id);

}
