package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Bot;

public interface BotService {
    Bot getBotByQuestionContainsOrKeywordContains(String question, String keyword);
    void saveBot(Bot bot);
}
