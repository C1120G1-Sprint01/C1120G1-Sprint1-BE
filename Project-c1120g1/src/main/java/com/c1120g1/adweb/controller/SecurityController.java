package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.common.AuthLogin;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.security.JwtResponse;
import com.c1120g1.adweb.security.JwtTokenUtil;
import com.c1120g1.adweb.service.AccountService;
import com.c1120g1.adweb.service.UserService;
import com.c1120g1.adweb.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class SecurityController {

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private AccountService accountService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody AuthLogin authLogin) {
        System.out.println(authLogin.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authLogin.getUsername(), authLogin.getPassword())
        );
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authentication.getName());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/api/checkEmail/{email}")
    public String checkEmail(@PathVariable(name = "email") String email) {
        System.out.println("Email : " + email);
        User user = this.userService.findByEmail(email);
        if (user != null) {
            String code = accountService.generateCode();
            System.out.println("CODE : "+code);
//            sendEmail(email, code);
            return code;
        }
        return "";
    }

    public void sendEmail(String email, String code) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email lấy lại mật khẩu từ Soren");
        message.setText("Chào bạn\n"
                + "TRANG WEB RAO VẶT C11 gửi mã code bên dưới để lấy lại mật khẩu\n"
                + "CODE : " + code + "\n"
                + "Thanks and regards!");

        this.emailSender.send(message);
    }
}
