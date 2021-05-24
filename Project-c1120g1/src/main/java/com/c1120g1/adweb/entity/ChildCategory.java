package com.c1120g1.adweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category", nullable = false, referencedColumnName = "category_id")
    private Category category;

    @OneToMany(mappedBy = "childCategory", cascade = CascadeType.ALL)
    private Set<Post> postSet;
}
