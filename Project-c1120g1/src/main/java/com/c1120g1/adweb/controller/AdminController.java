package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.dto.UserDTO;
import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import com.c1120g1.adweb.repository.WardRepository;
import com.c1120g1.adweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private AccountService accountService;
    @Autowired
    private WardRepository wardRepository;

    @GetMapping(value = "/admin/listUser", params = {"page", "size","onSorting","textSorting"})
    public ResponseEntity<Page<User>> listUser(@RequestParam("page") int page,
                                               @RequestParam("size") int size,
                                               @RequestParam("onSorting") boolean onSorting,
                                               @RequestParam("textSorting") String textSorting) {
        try {
            Page<User> users;
            if (textSorting.equals("")) {
                users = userService.findAllUser(PageRequest.of(page, size));
            } else {
                if (!onSorting) {
                    users = userService.findAllUser(PageRequest.of(page, size, Sort.by(textSorting).ascending()));
                } else {
                    users = userService.findAllUser(PageRequest.of(page, size, Sort.by(textSorting).descending()));
                }
            }
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Create new user
    @PostMapping(value = "/admin/listUser/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            List<User> userList = userService.findAll();
            if (!userList.isEmpty()) {
                Map<String, String> listError = new HashMap<>();
                List<Account> accountList = accountService.findAllAccount();
                if (!accountList.isEmpty()) {
                    for (Account account: accountList) {
                        if (account.getUsername().equals(userDTO.getUsername())) {
                            listError.put("existAccount", "Tai khoan ton tai");
                            break;
                        }
                    }
                }
                if (!userDTO.getNewPassword().equals(userDTO.getConfirmPassword())) {
                    listError.put("notCorrect", "Password wrong !");
                }
                for (User user: userList) {
                    if (user.getEmail().equals(userDTO.getEmail())) {
                        listError.put("existEmail", "Email has register yet !");
                    }
                    if (user.getPhone().equals(userDTO.getPhone())) {
                        listError.put("existPhone", "This phone has register yet !");
                    }
                }
                if (!listError.isEmpty()) {
                    return  ResponseEntity
                            .badRequest()
                            .body(listError);
                }
            }
            System.out.println(userDTO.getNewPassword());
            Account account = new Account();
            account.setUsername(userDTO.getUsername());
            account.setPassword(userDTO.getNewPassword());
            accountService.save(account);


            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setWard(wardRepository.getOne(userDTO.getWard()));
            user.setAccount(account);
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/admin/listUser")
//    public ResponseEntity<List<User>> getAllStudent() {
//        List<User> userList = this.userService.findAll();
//        if (userList == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(userList, HttpStatus.OK);
//    }
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

    @DeleteMapping("/admin/listUser/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        User userDelete = userService.findById(id);
        if (userDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userService.delete(id);
        return new ResponseEntity<>(userDelete,HttpStatus.OK);
    }

    @GetMapping("/admin/listUser")
    public List<User> fullTextSearch(@RequestParam(name = "q") String q) {
        return this.userService.fullSearch("%"+q+"%");
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
