package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
//    Danh sach user phan trang, sort
    Page<User> findAllUser(Pageable pageable);

    List<User> findAll();

    void save(User user);

    void updateUser(User user);

    void saveUser(Integer userId, String name, String email, String phone, Ward ward, String avatarUrl);

    void saveUsers(User user);

    User findById(Integer id);

    void delete(Integer id);

    boolean checkPass(Integer id, String password);



    Page<User> fullSearch(String q, Pageable pageable);

    User findByEmail(String email);


}
