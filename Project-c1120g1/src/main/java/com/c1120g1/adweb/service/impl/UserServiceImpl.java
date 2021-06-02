package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import com.c1120g1.adweb.repository.UserRepository;
import com.c1120g1.adweb.repository.WardRepository;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return repository.getAllUser(pageable);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(User user) {
        repository.createUser(standardizeName(user.getName()), user.getEmail(),
                user.getPhone(), user.getWard().getWardId(), user.getAccount().getUsername(), user.getAvatarUrl());
    }

//    ngoc - khong dc xoa nha
    @Override
    public void saveUser(Integer userId, String name, String email, String phone, Ward ward) {
        name = standardizeName(name);
        repository.updateUser(userId, name, email, phone, ward);
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public boolean checkPass(Integer id, String password) {
        return false;
    }

    @Override
    public Page<User> fullSearch(String q, Pageable pageable) {
        return repository.fullSearch(q, pageable);
    }


    // ngoc - chuan hoa ten
    public String standardizeName(String name) {
        name = name.toLowerCase();
        name = name.replaceAll("\\s+", " ");
        name = name.trim();
        String name2 = "";
        String temp = "";
        String[] words = name.split("\\s");
        for (String w : words) {
            temp = w.substring(0, 1).toUpperCase();
            for (int i = 1; i < w.length(); i++) {
                temp += w.charAt(i);
            }
            name2 += temp + " ";
        }
        name = name2.trim();
        return name;
    }

    @Autowired
    private WardRepository wardRepository;


    @Override
    public void saveUsers(User user) {
        repository.saveUser(user.getAvatarUrl(),
                standardizeName(user.getName()),
                user.getAccount().getUsername(),
                user.getEmail(),
                user.getPhone()
        );
    }



    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }





}
