package com.c1120g1.adweb.service;

import com.c1120g1.adweb.dto.PostStatisticDTO;
import com.c1120g1.adweb.dto.PostDTO;
import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    /**
     * author: ThinhTHB
     * method: search post by name
     */
    Page<Post> searchByName(String posterName, Pageable pageable);

    Page<Post> findAllListDetail(Pageable pageable);

    Page<Post> searchByTitle(String title, Pageable pageable);

    void cancelApprovePost(Integer id);

    Page<Post> findAllListApprove(Pageable pageable);

    void approvePost(Integer id);

    void deletePost(Integer id);

    void deleteById(Integer id);

    void waitPost(Integer id);

    Page<Post> findAllListWait(Pageable pageable);

    Page<Post> findAllByUsername(String username, Pageable pageable);

    Post findByIdAndUserId(Integer id);

    void updatePost(PostDTO postDTO);

    Page<Post> findAllNewest(Pageable pageable);

    //    ThuanNN
//    void save(String username, Post post);

    /**
     * Author: ThuanNN, ViNTT
     */
    void saveNewPost(Post post, String username);

    //    ThuanNN
    Page<Post> search(String keyword, Integer category, Integer province, Pageable pageable);

    String getPostDateTime();

    //    ViNTT
    Page<Post> findAllByCategoryName(String categoryName, Pageable pageable);

    Page<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable);

    /**
     * Author: ViNTT
     */
    Post findById(Integer postId);

    /**
     * Author: ViNTT
     */
    Post findActivePostById(Integer postId);

    /**
     * Author: ViNTT
     */
    Page<Post> findAllActiveByCategoryName(String categoryName, Pageable pageable);

    /**
     * Author: ViNTT
     */
    Page<Post> findAllActiveByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable);

    Page<Post> findAllByUsernameAndStatusId(String username, Integer statusId, Pageable pageable);

    List<Post> searchPostByTitle(String title);

    List<PostStatisticDTO> statisticQuantityPost(String startDate, String endDate);

    Page<Post> findAllPost(Pageable pageable);
}
