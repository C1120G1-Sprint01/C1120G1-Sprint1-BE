package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
            "where user.username = ?1 and post.enabled = true order by post.status_id", nativeQuery = true)
    Page<Post> findAllByUsername(String username, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update post " +
            "set description = ?1, email = ?2, " +
            "phone = ?3, post_type = ?4, " +
            "poster_name = ?5, price = ?6, " +
            "title = ?7, child_category_id = ?8, " +
            "status_id = ?9, ward_id = ?10 " +
            "where post_id = ?11", nativeQuery = true)
    void updatePost(String description, String email, String phone, boolean postType, String posterName, int price,
                    String title, int childCategoryId, int statusId, int wardId, int postId);

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

    @Query(value = "select * from post where enabled = 1 order by post_date_time desc", nativeQuery = true)
    Page<Post> findAllNewest(Pageable pageable);

    /**
     * Author: ViNTT
     * Get data for List Post By Child Category Page
     */
    @Query(value = "SELECT * FROM post " +
            "INNER JOIN child_category on post.child_category_id = child_category.child_category_id " +
            "INNER JOIN category on child_category.category_id = category.category_id " +
            "WHERE category.category_name like ?1", nativeQuery = true)
    Page<Post> findAllByCategoryName(String categoryName, Pageable pageable);

    /**
     * Author: ViNTT
     * Get data for List Post By Child Category Page
     */
    @Query(value = "SELECT * FROM post " +
            "INNER JOIN child_category on post.child_category_id = child_category.child_category_id " +
            "INNER JOIN category on child_category.category_id = category.category_id " +
            "WHERE category.category_name like ?1 AND child_category.child_category_name like ?2", nativeQuery = true)
    Page<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable);

}
