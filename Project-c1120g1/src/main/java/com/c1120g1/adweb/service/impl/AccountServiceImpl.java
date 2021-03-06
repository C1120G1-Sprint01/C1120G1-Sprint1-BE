package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.repository.AccountRepository;
import com.c1120g1.adweb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public List<Account> findAllAccount() {
        return repository.findAll();
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }


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
        return "" + (new Random().nextInt(900000) + 100000);
    }

    @Override
    public void saveUserAccount(Account account) {
        if (account.getUsername() == null) {
            account.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        repository.saveUserAccount(account.getUsername(), account.getPassword(), LocalDate.now());
    }

    @Override
    public Boolean checkPassword(Account account, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, account.getPassword());
    }

    @Override
    public void setNewPassword(Account account, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(newPassword));
        save(account);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return repository.getAccountByUsername(username);
    }

    @Override
    public void sendEmailApprove(String email) {
        SimpleMailMessage messageApprove = new SimpleMailMessage();
        messageApprove.setTo(email);
        messageApprove.setSubject("Email x??c nh???n b??i ????ng ???????c ph?? duy???t");
        messageApprove.setText( "Ch??c m???ng b???n! Tin c???a b???n ???? ???????c ????ng th??nh c??ng!" +
                                " Thanks and regards!");
        this.emailSender.send(messageApprove);
    }

    @Override
    public void sendEmailDelete(String email) {
        SimpleMailMessage messageDelete = new SimpleMailMessage();
        messageDelete.setTo(email);
        messageDelete.setSubject("Email th??ng b??o xo?? b??i ????ng");
        messageDelete.setText( "Xin th??ng b??o! Tin c???a b???n ???? b??? xo?? do vi ph???m!" +
                " N???u c?? b???t k?? th???c m???c n??o, b???n c?? th??? li??n h??? v???i Admin qua thanh chat. \n" +
                " Thanks and regards!");
        this.emailSender.send(messageDelete);
    }

    @Override
    public void sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email l???y l???i m???t kh???u t??? Hoangtq");
        message.setText("Ch??o b???n!\n"
                + "TRANG WEB RAO V???T C11 g???i m?? code OTP b??n d?????i ????? l???y l???i m???t kh???u.\n"
                + "M?? CODE bao g???m 6 s??? : " + code + "\n\n"
                + "Thanks and regards!");

        this.emailSender.send(message);
    }

    @Override
    public Account checkUserExists(String username) {
        return repository.checkUserExists(username);
    }
}

