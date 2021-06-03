//package com.c1120g1.adweb.controller;
//
//import com.c1120g1.adweb.common.AuthLogin;
//import com.c1120g1.adweb.entity.User;
//import com.c1120g1.adweb.security.JwtResponse;
//import com.c1120g1.adweb.security.JwtTokenUtil;
//import com.c1120g1.adweb.service.AccountService;
//import com.c1120g1.adweb.service.UserService;
//import com.c1120g1.adweb.service.impl.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(value = "*", allowedHeaders = "*")
//public class SecurityController {
//
//    @Autowired(required = false)
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private AccountService accountService;
//
//    @PostMapping("/api/login")
//    public ResponseEntity<?> login(@RequestBody AuthLogin authLogin) {
//        System.out.println(authLogin.getPassword());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authLogin.getUsername(), authLogin.getPassword())
//        );
//        UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authentication.getName());
//        String jwtToken = jwtTokenUtil.generateToken(userDetails);
//        JwtResponse jwtResponse = new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities());
//        return ResponseEntity.ok(jwtResponse);
//    }
//
//    @GetMapping("/api/checkEmail/{email}")
//    public ResponseEntity<String> checkEmail(@PathVariable(name = "email") String email) {
//        System.out.println("Email : " + email);
//        User user = this.userService.findByEmail(email);
//        if (user != null) {
//            String code = accountService.generateCode();
//            System.out.println("CODE : "+code);
////            accountService.sendEmail(email, code);
//            return new ResponseEntity<>(code, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("api/setNewPw/{email}/{newPw}")
//    public ResponseEntity<Void> setNewPassword(@PathVariable(name = "email") String email,
//                               @PathVariable(name = "newPw") String newPw){
//        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
//        User user = this.userService.findByEmail(email);
//        if (user != null){
//            user.getAccount().setPassword(bCryptEncoder.encode(newPw));
//            userService.save(user);
//            System.out.println("Set new Pw successfully");
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
