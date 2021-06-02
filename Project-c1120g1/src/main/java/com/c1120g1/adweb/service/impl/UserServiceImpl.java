package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.repository.UserRepository;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
<<<<<<< HEAD
    public User findAll(User user) {
        return repository.findAll(user);
=======
    public User findByEmail(String email) {
        return repository.findByEmail(email);
>>>>>>> 45fb757551547e0c3f079cc29a14566456c8840e
    }
}
