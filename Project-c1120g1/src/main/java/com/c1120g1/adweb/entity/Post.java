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
@Table(name = "`post`")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "poster_name", columnDefinition = "VARCHAR(50)")
    private String posterName;

    @Column(name = "phone", columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "title", columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(name = "post_type", columnDefinition = "BIT")
    private boolean postType;

    @Column(name = "post_date_time", columnDefinition = "DATETIME")
    private String postDateTime;

    @Column(name = "enabled", columnDefinition = "BIT")
    private boolean enabled;

    @Column(name = "price", columnDefinition = "INT")
    private Integer price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false, referencedColumnName = "status_id")
    private Status status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "child_category_id", nullable = false, referencedColumnName = "child_category_id")
    private ChildCategory childCategory;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id", nullable = false)
    private Ward ward;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Image> imageSet;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
