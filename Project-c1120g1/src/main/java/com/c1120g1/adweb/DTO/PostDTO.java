package com.c1120g1.adweb.dto;

import com.c1120g1.adweb.entity.Post;

public class PostDTO {

    private Post post;
    private String[] images;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
