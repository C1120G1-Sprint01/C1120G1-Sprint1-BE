package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.repository.UserRepository;
import com.c1120g1.adweb.repository.WardRepository;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private WardRepository wardRepository;



    @Override
    public void save(User user) {
       repository.saveUser(user.getAvatarUrl(),
                            user.getName(),
                            user.getAccount().getUsername(),
                            user.getEmail(),
                            user.getPhone()
       );
    }

    @Override
    public List<User> findAllUser() {
        return repository.findAll();
    }
}
