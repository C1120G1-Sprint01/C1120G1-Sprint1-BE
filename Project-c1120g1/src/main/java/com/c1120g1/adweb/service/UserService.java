package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //    Danh sach user phan trang, sort
    Page<User> findAllUser(Pageable pageable);

    List<User> findAll();

    void save(User user);

    void saveUser(Integer userId, String name, String email, String phone, Ward ward);

    User findById(Integer id);

    User findByUsername(String username);

    void delete(Integer id);

    boolean checkPass(Integer id, String password);

    List<User> fullSearch(String q);

    User findByEmail(String email);

    void saveUserCus(User user);

    List<User> findAllUser();

}
