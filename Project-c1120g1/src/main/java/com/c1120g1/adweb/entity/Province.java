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
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String provinceName;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private Set<District> districtSet;

}
