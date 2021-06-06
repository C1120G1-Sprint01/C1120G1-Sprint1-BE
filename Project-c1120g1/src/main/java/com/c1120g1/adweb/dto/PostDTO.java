package com.c1120g1.adweb.dto;

import com.c1120g1.adweb.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Post post;
    private String username;

}
