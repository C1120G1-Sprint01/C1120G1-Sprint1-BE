package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.dto.UserStatisticsDTO;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.c1120g1.adweb.dto.UserDTO;
import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import com.c1120g1.adweb.service.AccountService;
import com.c1120g1.adweb.service.UserService;
import com.c1120g1.adweb.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WardService wardService;

    /**
     * author: ThinhTHB
     * method: get List User Statistics
     * */
    @GetMapping(value = "/statistic", params = {"startDate", "endDate"})
    public ResponseEntity<List<UserStatisticsDTO>> getListUserStatistic(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        List<UserStatisticsDTO> userList = userService.statisticUser(startDate, endDate);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @PostMapping(value = "/user/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            List<User> userList = userService.findAll();
            if (!userList.isEmpty()) {
                Map<String, String> listError = new HashMap<>();
                List<Account> accountList = accountService.findAllAccount();
                if (!accountList.isEmpty()) {
                    if (accountService.getAccountByUsername(userDTO.getUsername()) != null) {
                        listError.put("existAccount", "Tài khoản đã tồn tại , vui lòng chọ tài khoản khác !");
                    }
                }
                if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                    listError.put("notCorrect", "Mật khẩu không trùng khớp , vui lòng nhập lại !");
                }
                if (userService.findByEmail(userDTO.getEmail()) != null) {
                    listError.put("existEmail", "Email đã tồn tại , vui lòng nhập email khác!");
                }
                if (!listError.isEmpty()) {
                    return ResponseEntity
                            .badRequest()
                            .body(listError);
                }
            }
            Account account = new Account();
            account.setUsername(userDTO.getUsername());
            account.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            accountService.saveUserAccount(account);

            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setWard(userDTO.getWard());
            user.setAvatarUrl(userDTO.getAvatarUrl());
            user.setAccount(account);
            userService.saveUserCus(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/user/getPass/{username}/{password}")
    public ResponseEntity<Boolean> checkPassword(@PathVariable(name = "username") String username,
                                                 @PathVariable(name = "password") String password) {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        boolean match = accountService.checkPassword(account, password);
        if (match) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/user/setPass/{username}/{newPassword}")
    public ResponseEntity<Void> setNewPassword(@PathVariable(name = "username") String username,
                                               @PathVariable(name = "newPassword") String newPassword) {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.setNewPassword(account, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Ward>> getWard() {
        try {
            List<Ward> wards = wardService.getAllWard();
            return ResponseEntity.ok().body(wards);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

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
}
