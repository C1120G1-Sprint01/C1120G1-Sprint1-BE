package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotRepository extends JpaRepository<Bot, Integer> {
    Bot getBotByQuestion(String question);

//    Bot getBotByQuestionContains(String question);

    @Query(value = "SELECT * \n" +
            "FROM bot \n" +
            "WHERE question like concat('%',?1,'%') ", nativeQuery = true)
    List<Bot> getBotByQuestionContains(String question);


}
