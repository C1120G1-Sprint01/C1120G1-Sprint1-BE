package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
<<<<<<< HEAD
    User findAll(User user);
=======
    User findByEmail(String email);
>>>>>>> 45fb757551547e0c3f079cc29a14566456c8840e
}
