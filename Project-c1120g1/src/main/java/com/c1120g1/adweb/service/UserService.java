package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.c1120g1.adweb.dto.UserStatisticsDTO;
import org.springframework.validation.Errors;

import java.util.List;


public interface UserService {

    //    Danh sach user phan trang, sort
    Page<User> findAllUser(Pageable pageable);

    List<User> findAll();

    void save(User user);

    void validate(User user, Errors errors);

    User findById(Integer id);

    User findByUsername(String username);

    void delete(Integer id);

    boolean checkPass(Integer id, String password);

    List<User> fullSearch(String q);

    User findByEmail(String email);

    void saveUserCus(User user);

    List<User> findAllUser();

    void updateUser(User user);

    /**
     * author: ThinhTHB
     * method: get List User Statistics
     * */
    List<UserStatisticsDTO> statisticUser(String startDate, String endDate);

}
