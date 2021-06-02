package com.c1120g1.adweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "`bot`")
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bot_id")
    private Integer botId;

    @Column(name = "question", columnDefinition = "VARCHAR(255)")
    private String question;

    @Column(name = "keyword", columnDefinition = "VARCHAR(255)")
    private String keyword;

    @Column(name = "answer", columnDefinition = "VARCHAR(255)")
    private String answer;

    public Bot() {
    }

    public Integer getBotId() {
        return botId;
    }

    public void setBotId(Integer botId) {
        this.botId = botId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
