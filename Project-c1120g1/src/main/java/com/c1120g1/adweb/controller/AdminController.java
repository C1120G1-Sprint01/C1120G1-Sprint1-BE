package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import com.c1120g1.adweb.repository.WardRepository;
import com.c1120g1.adweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private WardService wardService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private WardRepository wardRepository;


//    Ngoc - Get list, Pagination

    @GetMapping(value = "/admin/listUser")
    public ResponseEntity<?> listUser() {
        try {
            List<User> userList = this.userService.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //   Ngoc -  Create new user
    @PostMapping(value = "/admin/listUser/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody com.c1120g1.adweb.dto.UserDTO userDTO) {
        try {
            List<User> userList = userService.findAll();
            if (!userList.isEmpty()) {
                Map<String, String> listError = new HashMap<>();
                Account account = accountService.checkUserExists(userDTO.getUsername());
                if (account != null) {
                    listError.put("existAccount", "Tài khoản đã tồn tại trong hệ thống");
                }
                if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                    listError.put("notCorrect", "Xác thực mật khẩu sai, mời bạn nhập lại !");
                }
                for (User user : userList) {
                    if (user.getEmail().equals(userDTO.getEmail())) {
                        listError.put("existEmail", "Email này đã được đăng ký !");
                    }
                    if (user.getPhone().equals(userDTO.getPhone())) {
                        listError.put("existPhone", "Số điện thoại này đã được đăng ký !");
                    }
                }
                if (!listError.isEmpty()) {
                    return ResponseEntity
                            .badRequest()
                            .body(listError);
                }
            }

            // chuyen method nay vao trong service
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Account account = new Account();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String registerDate = format.format(new Date());
            account.setUsername(userDTO.getUsername());
            account.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            account.setRegisterDate(registerDate);
            accountService.saveUserAccount(account);

            System.out.println("Pw Bcrypt64 : " + account.getPassword());

            // chuyen method nay vao trong service
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setWard(userDTO.getWard());
            user.setAccount(account);
            user.setAvatarUrl(userDTO.getAvatarUrl());
            userService.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("get Exception");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //   ngoc -  check bad request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    //    Ngoc - Get User by id
    @GetMapping("/admin/listUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        if (user != null) {
            System.err.println("user");
            System.err.println("url " +user.getAvatarUrl());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //    Ngoc - Update user
    @PutMapping(value = "/admin/listUser/edit/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> editUser(@PathVariable("id") Integer id, @RequestBody User user) {
        try {
            User user1 = userService.findById(id);
            if (user1 != null) {
                user.setUserId(id);
                user.setAccount(user1.getAccount());
                userService.updateUser(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    //    Ngoc - Delete User
    @DeleteMapping("/admin/listUser/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        User userDelete = userService.findById(id);
        if (userDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userService.delete(id);
        return new ResponseEntity<>(userDelete, HttpStatus.OK);
    }

    //    Ngoc - full text search
//    params = {"size", "q"}
    @GetMapping(value = "/admin/listUser/search")
    public ResponseEntity<List<User>> fullTextSearch(@RequestParam(name = "q") String q) {
        try {
            List<User> userList1 = this.userService.fullSearch(q);
            if (userList1.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(userList1, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    //    get du lieu tu bang ward
    public ResponseEntity<List<Ward>> getWard() {
        try {
            List<Ward> wards = wardService.getAllWard();
            return ResponseEntity.ok().body(wards);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
