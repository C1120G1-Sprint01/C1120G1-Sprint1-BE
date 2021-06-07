package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Role;
import com.c1120g1.adweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
