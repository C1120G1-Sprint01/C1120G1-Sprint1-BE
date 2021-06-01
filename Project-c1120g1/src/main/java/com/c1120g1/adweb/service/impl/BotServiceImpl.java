package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Bot;
import com.c1120g1.adweb.repository.BotRepository;
import com.c1120g1.adweb.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    private BotRepository repository;

    @Override
    public Bot getBotByQuestion(String question) {
        return repository.getBotByQuestion(question);
    }

    @Override
    public List<Bot> getBotByQuestionContains(String question) {
        return repository.getBotByQuestionContains(question);
    }

    @Override
    public void saveBot(Bot bot) {
        repository.save(bot);
    }
}
