package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    void save(User user);
    List<User> findAllUser();
}
