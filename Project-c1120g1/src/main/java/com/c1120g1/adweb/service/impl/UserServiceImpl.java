package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.dto.UserStatisticsDTO;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;

import com.c1120g1.adweb.repository.UserRepository;
import com.c1120g1.adweb.repository.WardRepository;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private WardRepository wardRepository;

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
                user.getAccount().getUsername(),user.getAvatarUrl());
    }

    @Override
    public void validate(User user, Errors errors) {
        for (User u: findAllUser()) {
            if (u.getAccount().getUsername().equals(user.getAccount().getUsername())) {
                errors.rejectValue("username", "checkDuplicateUsername");
            }
            if (u.getEmail().equals(user.getEmail())) {
                errors.rejectValue("email", "checkDuplicateEmail");
            }
            if (u.getPhone().equals(user.getPhone())) {
                errors.rejectValue("phone", "checkDuplicatePhone");
            }
        }
    }


    @Override
    public void saveUserCus(User user) {
        repository.saveUserCus(user.getAvatarUrl(),
                standardizeName(user.getName()),
                user.getAccount().getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getWard().getWardId());
    }

    @Override
    public List<User> findAllUser() {
        return repository.findAll();
    }

    @Override
    public void updateUser(User user) {
        repository.updateUser(user.getUserId(), standardizeName(user.getName()),
                user.getEmail(), user.getPhone(), user.getWard(),user.getAvatarUrl().replace("imgChange/", "imgChange%2F"));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
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

    /**
     * author: ThinhTHB
     * method: get List User Statistics
     * */
    @Override
    public List<UserStatisticsDTO> statisticUser(String startDate, String endDate) {
        return repository.userStatistics(startDate, endDate);
    }

//    String name, String email, String phone, String username, String avatarUrl, Integer wardId
    @Override
    public void saveUserSocial(User user) {
        repository.saveUserSocial(  user.getName(),user.getEmail(), "",
                                    user.getAccount().getUsername(),user.getAvatarUrl() , null);
    }
}
