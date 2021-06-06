package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.repository.AccountRepository;
import com.c1120g1.adweb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void saveAccount(Account account) {
        if (account.getUsername() == null) {
            account.setRegisterDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        }
    }

    @Override
    public List<Account> getAllAccount() {
        return repository.findAll();

    }

//    ThuanNN: edit return null
    @Override
    public Account findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public String generateCode() {
        return ""+ (int)(Math.random()*1000000*1.1);
    }

    @Override
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

