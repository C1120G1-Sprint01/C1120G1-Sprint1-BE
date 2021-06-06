package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.dto.BotDTO;
import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.AccountRole;
import com.c1120g1.adweb.entity.Bot;
import com.c1120g1.adweb.entity.Role;
import com.c1120g1.adweb.service.AccountRoleService;
import com.c1120g1.adweb.service.AccountService;
import com.c1120g1.adweb.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bot")
public class BotController {
    @Autowired
    BotService botService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;

    @PostMapping("")
    public ResponseEntity<Bot> getBotByQuestion(@RequestBody String question) {
        try {
            Bot bot = botService.getBotByQuestion(question);
            if (bot == null) {
                List<Bot> botContains = botService.getBotByQuestionContains(question);
                if (botContains.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(botContains.get(0), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(bot, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/study")
    public ResponseEntity<Bot> studyForBot(@RequestBody BotDTO botDTO) {
        try {
            if (botDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                Bot bot = new Bot();
                bot.setQuestion(botDTO.getQuestion());
                bot.setAnswer(botDTO.getAnswer());
                botService.saveBot(bot);
                return new ResponseEntity<>(bot, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/account")
    public ResponseEntity<Account> getAccountByUserName(@RequestBody String userName) {
        try {
            Account account = accountService.findByUsername(userName);
            if (account == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/accountRole")
    public ResponseEntity<Role> getAccountRoleByAccount(@RequestBody Account account) {
        try {
            AccountRole accountRole = accountRoleService.findAccountRoleByAccount(account);
            if (accountRole == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(accountRole.getRole(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
