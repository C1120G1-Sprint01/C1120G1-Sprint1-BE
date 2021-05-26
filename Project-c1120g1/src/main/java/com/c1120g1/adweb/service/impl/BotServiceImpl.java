package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.BotRepository;
import com.c1120g1.adweb.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    private BotRepository repository;
}
