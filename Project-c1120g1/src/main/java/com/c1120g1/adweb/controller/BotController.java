package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Bot;
import com.c1120g1.adweb.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bot")
public class BotController {
    @Autowired
    BotService botService;

    @GetMapping("")
    public ResponseEntity<Bot> getBotByQuestion(@RequestBody String question) {
        try {
            Bot bot = botService.getBotByQuestionContainsOrKeywordContains(question, question);
            if (bot == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(bot, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/study")
    public ResponseEntity<Bot> studyForBot(@RequestBody Bot bot) {
        try {
            if (bot == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                botService.saveBot(bot);
                return new ResponseEntity<>(bot, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
