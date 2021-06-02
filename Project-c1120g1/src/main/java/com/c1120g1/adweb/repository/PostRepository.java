package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import javax.transaction.Transactional;

@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value =  "select * " +
                    "from post " +
                    "where status_id = 1", nativeQuery = true)
    Page<Post> findAllListDetail(Pageable pageable);

    @Query(value =   "select * " +
                     "from post " +
                     "where status_id = 2", nativeQuery = true)
    Page<Post> findAllListApprove(Pageable pageable);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value =  "update post " +
                    "set status_id = 1 " +
                    "where post_id = ?1", nativeQuery = true)
    void approvePost(Integer id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value =  "update post " +
                    "set status_id = 6 " +
                    "where post_id = ?1", nativeQuery = true)
    void waitPost(Integer id);

    @Query(value =  "select * " +
                    "from post " +
                    "where status_id = 6", nativeQuery = true)
    Page<Post> findAllListWait(Pageable pageable);

    @Query(value = "select * " +
            "from post " +
            "inner join user " +
            "on post.user_id = user.user_id " +
            "where username = ?1 order by status_id", nativeQuery = true)
    Page<Post> findAllByUsername(String username, Pageable pageable);

    @Query(value = "select * from post " +
            "inner join ward on post.ward_id = ward.ward_id " +
            "inner join district on district.district_id = ward.district_id " +
            "inner join province on province.province_id = district.province_id " +
            "inner join child_category on child_category.child_category_id = post.child_category_id " +
            "where (  " +
            "        (post.title like ?1) &&  " +
            "        (child_category.child_category_name = ?2) &&  " +
            "        (province.province_name = ?3)  " +
            "       )", nativeQuery = true)
    List<Post> search(String title, String child_category, String province_name);

    @Query(value="select * from post where enabled = 1 order by post_date_time desc", nativeQuery = true)
    Page<Post> findAllNewest(Pageable pageable);
}
