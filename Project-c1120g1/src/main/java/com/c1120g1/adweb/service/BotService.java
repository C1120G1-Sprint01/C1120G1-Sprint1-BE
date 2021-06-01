package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Bot;

import java.util.List;

public interface BotService {
    Bot getBotByQuestion(String question);
    List<Bot> getBotByQuestionContains(String question);
    void saveBot(Bot bot);
}
