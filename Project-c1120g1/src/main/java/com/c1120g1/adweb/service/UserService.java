package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

//    User findAll(User user);


    User findByEmail(String email);

    void saveUserCus(User user);

    List<User> findAllUser();

    User findById(Integer id);

    void save(User user);

    User findByUsername(String username);
}
