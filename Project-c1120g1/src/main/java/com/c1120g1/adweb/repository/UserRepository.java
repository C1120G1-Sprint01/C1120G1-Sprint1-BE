package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
