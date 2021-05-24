package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
