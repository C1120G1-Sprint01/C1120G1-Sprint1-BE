package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    //    ngoc - tim kiem full text search
    @Query(value = "select * from user" +
            "inner join ward on user.wardId = ward.ward_id" +
            "where concat (user_id, email, name, phone, ward.ward_name) like?1",
            nativeQuery =true)
    List<User> fullSearch(String q);

    //   ngoc - list user su dung DTO class
    @Query("SELECT u FROM User u")
    Page<User> getAllUser(Pageable pageable);

    //    ngoc - them moi user
    @Modifying
    @Query(value = "insert into User (name, email, phone, ward_id, username)" +
            "values (:name, :email, :phone, :wardId, :username)",
            nativeQuery = true)
    @Transactional
    void createUser(@Param("name") String name, @Param("email") String email,
                    @Param("phone") String phone, @Param("wardId") Integer wardId, @Param("username") String username);

    //    ngoc - edit user
    @Modifying
    @Query(value ="update User u" +
            " set u.name = ?2, " +
            "u.email =?3, " +
            "u.phone =?4, " +
            "u.ward =?5 " +
            "where u.userId = ?1")
    void updateUser(Integer userId, String name, String email, String phone, Ward ward);

    @Modifying
    @Query(value = "INSERT INTO `user` ( avatar_Url, email, `name`, phone, username, ward_id) " +
            "values " + "(:avatarUrl, "+":email," + ":name," + ":phone," + ":username," +":wardId) ",
            nativeQuery = true)
    @Transactional
    void saveUserCus(   @Param("avatarUrl")String avatarUrl,
                        @Param("name") String name,
                        @Param("username") String username,
                        @Param("email") String email,
                        @Param("phone") String phone,
                        @Param("wardId") Integer wardId);

    User findByEmail(String email);

    /**
     * Author: ViNTT
     */
    @Query(value = "SELECT * FROM `user` " +
            "WHERE username LIKE ?1", nativeQuery = true)
    User findByUsername(String username);
}
