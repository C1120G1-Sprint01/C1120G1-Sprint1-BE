package com.c1120g1.adweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ward")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ward_id")
    private Integer wardId;

    @Column(name = "ward_name")
    private String wardName;

    @ManyToOne
    @JsonIgnoreProperties("wardSet")
    @JoinColumn(name = "district_id", referencedColumnName = "district_id", nullable = false)
    private District district;

    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ward")
    private Set<User> userSet;

    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ward")
    private Set<Post> postSet;

}
