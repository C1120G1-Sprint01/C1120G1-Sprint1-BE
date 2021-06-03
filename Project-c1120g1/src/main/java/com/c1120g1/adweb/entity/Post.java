package com.c1120g1.adweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "price", columnDefinition = "INT")
    private Integer price;

    @NotBlank
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;


    @ManyToOne
    @JsonIgnoreProperties("postSet")
    @JoinColumn(name = "status_id", nullable = false, referencedColumnName = "status_id")
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("postSet")
    @JoinColumn(name = "child_category_id", nullable = false, referencedColumnName = "child_category_id")
    private ChildCategory childCategory;

    @ManyToOne
    @JsonIgnoreProperties({"postSet", "userSet"})
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_id", nullable = false)
    private Ward ward;

    @ManyToOne
    @JsonIgnoreProperties("postSet")
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("post")
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPostType() {
        return postType;
    }

    public void setPostType(boolean postType) {
        this.postType = postType;
    }

    public String getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(String postDateTime) {
        this.postDateTime = postDateTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ChildCategory getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(ChildCategory childCategory) {
        this.childCategory = childCategory;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }
}
