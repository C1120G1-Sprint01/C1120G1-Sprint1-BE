package com.c1120g1.adweb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

}
