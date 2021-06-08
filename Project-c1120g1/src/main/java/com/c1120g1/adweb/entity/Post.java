package com.c1120g1.adweb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @Size(max = 50)
    @Column(name = "poster_name", columnDefinition = "VARCHAR(50)")
    private String posterName;

    @NotBlank
    @Pattern(regexp = "^[\\d]{10,11}$")
    @Column(name = "phone", columnDefinition = "VARCHAR(20)")
    private String phone;

    @NotBlank
    @Email
    @Size(max = 50)
    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @NotBlank
    @Size(max = 50)
    @Column(name = "title", columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(name = "post_type", columnDefinition = "BIT")
    private boolean postType;

    @Column(name = "post_date_time", columnDefinition = "DATETIME")
    private String postDateTime;

    @Column(name = "enabled", columnDefinition = "BIT")
    private boolean enabled;

    @Min(0)
    @Max(2000000000)
    @Column(name = "price", columnDefinition = "DOUBLE")
    private Long price;

    @NotBlank
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false, referencedColumnName = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "child_category_id", nullable = false, referencedColumnName = "child_category_id")
    private ChildCategory childCategory;

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id", nullable = false)
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Image> imageSet;

    @Override
    public String toString() {
        return "Post{" +
                "posterName='" + posterName + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", postDateTime='" + postDateTime + '\'' +
                ", enabled=" + enabled +
                ", status=" + status +
                ", ward=" + ward +
                ", imageSet=" + imageSet +
                '}';
    }

}
