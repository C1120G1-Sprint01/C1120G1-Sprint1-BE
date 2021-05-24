package com.c1120g1.adweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "image_name", columnDefinition = "VARCHAR(50)")
    private String imageName;

    @Column(name = "url", columnDefinition = "VARCHAR(255)")
    private String url;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post", nullable = false, referencedColumnName = "post_id")
    private Post post;
}
