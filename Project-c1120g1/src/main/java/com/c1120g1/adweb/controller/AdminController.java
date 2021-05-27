package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.service.DistrictService;
import com.c1120g1.adweb.service.ProvinceService;
import com.c1120g1.adweb.service.UserService;
import com.c1120g1.adweb.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private WardService wardService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @GetMapping("/admin/listUser")
    public List<User> getAllStudent() {
        return userService.findAll();
    }
    @GetMapping("/admin/listUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/admin/listUser/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User editUser = userService.findById(id);
        if (editUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        editUser.setName(user.getName());
        editUser.setEmail(user.getEmail());
        editUser.setPhone(user.getPhone());
        editUser.setWard(user.getWard());
        this.userService.save(editUser);
        return new ResponseEntity<>(editUser, HttpStatus.OK);
    }

}
