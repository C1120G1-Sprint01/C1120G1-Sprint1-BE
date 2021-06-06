package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.dto.PostStatisticDTO;
import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {

    /**
     * author: ThinhTHB
     * method: search post by name
     */
    @Query(value = "select p from Post p " +
            "where p.posterName like %:posterName%")
    List<Post> searchByName(String posterName);

    @Query(value = "select * " +
            "from post " +
            "where status_id = 1 and enabled = 1", nativeQuery = true)
    Page<Post> findAllListDetail(Pageable pageable);

    @Query(value =  "select * " +
                    "from post " +
                    "where status_id = 1 and enabled = 1 and title like %?1%", nativeQuery = true)
    Page<Post> searchByTitle(String title, Pageable pageable);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update post " +
            "set status_id = 2 " +
            "where post_id = ?1", nativeQuery = true)
    void cancelApprovePost(Integer id);

    @Query(value = "select * " +
            "from post " +
            "where status_id = 2 and enabled = 1", nativeQuery = true)
    Page<Post> findAllListApprove(Pageable pageable);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update post " +
            "set status_id = 1 " +
            "where post_id = ?1", nativeQuery = true)
    void approvePost(Integer id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update post " +
            "set enabled = 0 " +
            "where post_id = ?1", nativeQuery = true)
    void deletePost(Integer id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update post " +
            "set status_id = 6 " +
            "where post_id = ?1", nativeQuery = true)
    void waitPost(Integer id);

    @Query(value = "select * " +
            "from post " +
            "where status_id = 6 and enabled = 1", nativeQuery = true)
    Page<Post> findAllListWait(Pageable pageable);

    @Query(value = "select * " +
            "from post " +
            "inner join user " +
            "on post.user_id = user.user_id " +
            "where user.username = ?1 and post.enabled = true order by post.status_id", nativeQuery = true)
    Page<Post> findAllByUsername(String username, Pageable pageable);

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

    @Query(value = "select * from post " +
            "where enabled = 1 and status_id = 1 " +
            "order by post_date_time desc", nativeQuery = true)
    Page<Post> findAllNewest(Pageable pageable);

    /**
     * Author: ViNTT
     */
    @Query(value = "SELECT * FROM post " +
            "WHERE post_id = ?1 AND enabled = 1 AND status_id = 1", nativeQuery = true)
    Post findActivePostById(Integer postId);

    /**
     * Author: ViNTT
     */
    @Query(value = "SELECT * FROM post " +
            "INNER JOIN child_category on post.child_category_id = child_category.child_category_id " +
            "INNER JOIN category on child_category.category_id = category.category_id " +
            "WHERE category.category_name LIKE ?1 AND post.enabled = 1 AND post.status_id = 1 " +
            "ORDER BY post.post_date_time DESC", nativeQuery = true)
    Page<Post> findAllActiveByCategoryName(String categoryName, Pageable pageable);

    /**
     * Author: ViNTT
     */
    @Query(value = "SELECT * FROM post " +
            "INNER JOIN child_category on post.child_category_id = child_category.child_category_id " +
            "INNER JOIN category on child_category.category_id = category.category_id " +
            "WHERE category.category_name like ?1 AND child_category.child_category_name like ?2 AND post.enabled = 1 AND post.status_id = 1 " +
            "ORDER BY post.post_date_time DESC", nativeQuery = true)
    Page<Post> findAllActiveByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable);

    @Query(value = "select * " +
            "from post " +
            "inner join user " +
            "on post.user_id = user.user_id " +
            "where user.username = ?1 and post.enabled = true and post.status_id = ?2", nativeQuery = true)
    Page<Post> findAllByUsernameAndStatusId(String username, Integer statusId, Pageable pageable);

    @Query(value = "SELECT post_date_time as timePost, " +
            "COUNT(case when status_id=4 then 1 end  ) as countPostSuccess, \n" +
            "COUNT(case when status_id=5 then 1 end  ) as countPostFailure \n" +
            "FROM post\n" +
            "GROUP BY post_date_time\n" +
            "HAVING  date(post_date_time) between ?1 and ?2", nativeQuery = true)
    List<PostStatisticDTO> statisticQuantityPost(String startDate, String endDate);

    @Query(value = "select * from post where enabled = 1 order by post_date_time desc", nativeQuery = true)
    Page<Post> findAllPost(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE post \n" +
            "SET enabled = 0 \n" +
            "WHERE post_id = ?1 and enabled = 1;", nativeQuery = true)
    void deleteById(Integer id);
}
