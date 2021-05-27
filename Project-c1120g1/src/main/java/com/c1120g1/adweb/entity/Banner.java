package com.c1120g1.adweb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Integer bannerId;

    @Column(name = "position", columnDefinition = "VARCHAR(50)")
    private String position;

    @Column(name = "size", columnDefinition = "VARCHAR(50)")
    private String size;

    @Column(name = "duration", columnDefinition = "DATETIME(6)")
    private String duration;

    @Column(name = "image", columnDefinition = "VARCHAR(255)")
    private String image;

}
