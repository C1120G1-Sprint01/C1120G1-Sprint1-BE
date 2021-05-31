package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("userList")
    public Iterable<User> userList() {
        return  userRepository.findAll();
    }
}
