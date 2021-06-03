package com.c1120g1.adweb.controller;



import com.c1120g1.adweb.dto.UserDTO;
import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.service.AccountService;
import com.c1120g1.adweb.service.UserService;
import com.c1120g1.adweb.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private WardService wardService;


    @PostMapping(value = "/user/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody UserDTO userDTO) {
        try {
            List<Account> accountList = accountService.getAllAccount();
            if (!accountList.isEmpty()) {
                for (Account account : accountList) {
                    if (account.getUsername().equals(userDTO.getUsername())) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }
            }
            if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<User> userList = userService.findAll();
            if (!userList.isEmpty()) {
                Map<String, String> listError = new HashMap<>();
                for (User user : userList) {
                    if (user.getEmail().equals(userDTO.getEmail())) {
                        listError.put("existEmail", "Email đã được đăng ký!");
                    }
                    if (user.getAccount().getUsername().equals(userDTO.getUsername())) {
                        listError.put("existUsername", "Tên tài khoản đã được đăng ký!");
                    }
                }
                if (!listError.isEmpty()) {
                    return ResponseEntity
                            .badRequest()
                            .body(listError);
                }
            }
            Account account = new Account();
            account.setUsername(userDTO.getUsername());
            account.setPassword(userDTO.getPassword());
            accountService.saveAccount(account);

            User user = new User();
            user.setName(userDTO.getName());
            user.setAvatarUrl(userDTO.getAvatarUrl());
            user.setAccount(account);
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

