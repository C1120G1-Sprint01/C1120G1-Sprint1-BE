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
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


//    ngoc - tim kiem full text search
    @Query(value =" select * from user" +
           " inner join ward on user.ward_id = ward.ward_id" +
            " where concat (user_id, email, `name`, phone, ward.ward_name) like %?1%",
            nativeQuery =true)
    Page<User> fullSearch(String q, Pageable pageable);

//   ngoc - list user su dung DTO class
    @Query("SELECT u FROM User u")
    Page<User> getAllUser(Pageable pageable);

//    ngoc - them moi user
    @Modifying
    @Query(value = "insert into User (`name`, email, phone, ward_id, username, avatar_url)" +
            " values (:name, :email, :phone, :ward_id, :username, :avatar_url)",
                nativeQuery = true)
    @Transactional
    void createUser(@Param("name") String name, @Param("email") String email,
                    @Param("phone") String phone, @Param("ward_id") Integer ward_id, @Param("username") String username, @Param("avatar_url") String avatar_url);



//    ngoc - edit user
    @Modifying
    @Query(value ="update User u" +
            " set u.name = ?2, " +
            "u.email =?3, " +
            "u.phone =?4, " +
            "u.ward =?5, " +
            "u.avatarUrl =?6" +
            " where u.userId = ?1")
    void updateUser(Integer userId, String name, String email, String phone, Ward ward, String avatarUrl);




    @Modifying
    @Query(value = "INSERT INTO `user` ( avatar_Url, email, name, phone, username) " +
            "values " + "(:avatarUrl "+":email," + ":name," + ":phone," + ":account,", nativeQuery = true)
    @Transactional
    void saveUser(   @Param("avatar")String avatarUrl,
                        @Param("name") String name,
                        @Param("account") String account,
                        @Param("email") String email,
                        @Param("phone") String phone);


    User findByEmail(String email);

}
