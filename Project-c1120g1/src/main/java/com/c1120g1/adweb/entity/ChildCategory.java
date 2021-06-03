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
@Table(name = "child_category")
public class ChildCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_category_id")
    private Integer childCategoryId;

    @Column(name = "child_category_name", columnDefinition = "VARCHAR(50)")
    private String childCategoryName;

    @ManyToOne
    @JsonIgnoreProperties("childCategorySet")
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    private Category category;

    @OneToMany(mappedBy = "childCategory", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("childCategory")
    private Set<Post> postSet;

    private Boolean deleteFlag;

}
