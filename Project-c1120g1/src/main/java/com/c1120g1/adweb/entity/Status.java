package com.c1120g1.adweb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`status`")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", columnDefinition = "INT NOT NULL")
    private Integer statusId;

    @Column(name =  "status_name", columnDefinition = "VARCHAR(50)")
    private String statusName;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private Set<Post> postSet;

}
