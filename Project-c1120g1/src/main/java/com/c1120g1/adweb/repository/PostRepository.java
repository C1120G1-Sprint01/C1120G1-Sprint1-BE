package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
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
}
