package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.repository.AccountRepository;
import com.c1120g1.adweb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Account findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public String generateCode() {
        return ""+ (int)(Math.random()*1000000*1.1);
    }

    @Override
    public void sendEmailApprove(String email, String code) {
        SimpleMailMessage messageApprove = new SimpleMailMessage();
        messageApprove.setTo(email);
        messageApprove.setSubject("Email xác nhận bài đăng được phê duyệt");
        messageApprove.setText("Chúc mừng bạn! Tin của bạn đã được đăng thành công!" +
                                "CODE: " + code +
                                "Thanks and regards!");
        this.javaMailSender.send(messageApprove);
    }
}

