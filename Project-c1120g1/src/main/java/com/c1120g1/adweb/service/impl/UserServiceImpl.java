package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.repository.UserRepository;
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
                user.getPhone(), user.getWard().getWardId(),
                user.getAccount().getUsername());
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
    public List<User> fullSearch(String q) {
        return repository.fullSearch(q);
    }

    // chuan hoa ten
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
}
