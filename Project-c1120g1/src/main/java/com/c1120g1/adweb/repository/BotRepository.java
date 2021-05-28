package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot, Integer> {
    Bot getBotByQuestionContainsOrKeywordContains(String question, String keyword);
}
