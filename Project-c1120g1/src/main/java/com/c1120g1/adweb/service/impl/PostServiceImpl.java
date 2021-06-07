package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.dto.PostStatisticDTO;
import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.repository.ImageRepository;
import com.c1120g1.adweb.entity.Status;
import com.c1120g1.adweb.dto.PostDTO;
import com.c1120g1.adweb.repository.PostRepository;
import com.c1120g1.adweb.service.PostService;
import com.c1120g1.adweb.service.StatusService;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private StatusService statusService;

    /**
     * author: ThinhTHB
     * method: search post by name
     */
    @Override
    public List<Post> searchByName(String posterName) {
        return repository.searchByName(posterName);
    }

    @Override
    public Page<Post> findAllByUsername(String username, Pageable pageable) {
        return repository.findAllByUsername(username, pageable);
    }

    @Override
    public Page<Post> findAllListDetail(Pageable pageable) {
        return repository.findAllListDetail(pageable);
    }

    @Override
    public Page<Post> searchByTitle(String title, Pageable pageable) {
        return repository.searchByTitle(title, pageable);
    }

    @Override
    public void cancelApprovePost(Integer id) {
        repository.cancelApprovePost(id);
    }

    /**
     * Author: ViNTT
     */
    @Override
    public Post findById(Integer postId) {
        return repository.findById(postId).orElse(null);
    }

    /**
     * Author: ViNTT
     */
    @Override
    public Post findActivePostById(Integer postId) {
        return repository.findActivePostById(postId);
    }

    /**
     * Author: ViNTT
     */
    @Override
    public Page<Post> findAllActiveByCategoryName(String categoryName, Pageable pageable) {
        categoryName = categoryName.replace("-", " ");
        return repository.findAllActiveByCategoryName(categoryName, pageable);
    }

    /**
     * Author: ViNTT
     */
    @Override
    public Page<Post> findAllActiveByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable) {
        categoryName = categoryName.replace("-", " ");
        childCategoryName = childCategoryName.replace("-", " ");
        return repository.findAllActiveByCategoryNameAndChildCategoryName(categoryName, childCategoryName, pageable);
    }

    @Override
    public Post findByIdAndUserId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        String postDateTime = postService.getPostDateTime();
        repository.updatePost(postDTO.getPost().getDescription(),
                postDTO.getPost().getEmail(),
                postDTO.getPost().getPhone(),
                postDTO.getPost().isPostType(),
                postDTO.getPost().getPosterName(),
                postDTO.getPost().getPrice(),
                postDTO.getPost().getTitle(),
                postDTO.getPost().getChildCategory().getChildCategoryId(),
                postDTO.getPost().getStatus().getStatusId(),
                postDTO.getPost().getWard().getWardId(),
                postDateTime,
                postDTO.getPost().getPostId());
        if (postDTO.getImages().length != 0) {
            imageRepository.delete(postDTO.getPost().getPostId());
        }
        for (String url : postDTO.getImages()) {
            imageRepository.save(url, postDTO.getPost().getPostId());
        }
    }

    @Override
    public Page<Post> findAllListApprove(Pageable pageable) {
        return repository.findAllListApprove(pageable);
    }

    @Override
    public void approvePost(Integer id) {
        repository.approvePost(id);
    }

    @Override
    public void deletePost(Integer id) {
        repository.deletePost(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void waitPost(Integer id) {
        repository.waitPost(id);
    }

    @Override
    public Page<Post> findAllListWait(Pageable pageable) {
        return repository.findAllListWait(pageable);
    }

    @Override
    public Page<Post> findAllNewest(Pageable pageable) {
        return repository.findAllNewest(pageable);
    }

    /**
     * Author: ThuanNN, ViNTT
     */
    @Override
    public void saveNewPost(Post post, String username) {
        String postDateTime = postService.getPostDateTime();
        Status status = statusService.findById(2);
        User user = userService.findByUsername(username);

        post.setPostDateTime(postDateTime);
        post.setEnabled(true);
        post.setStatus(status);
        post.setUser(user);

        repository.save(post);
    }

    @Override
    public List<Post> search(String title, String child_category, String province_name) {
        return repository.search(title, child_category, province_name);
    }

    @Override
    public String getPostDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date now = new Date();
        return simpleDateFormat.format(now);
    }

    @Override
    public Page<Post> findAllByUsernameAndStatusId(String username, Integer statusId, Pageable pageable) {
        return repository.findAllByUsernameAndStatusId(username, statusId, pageable);
    }

    @Override
    public List<PostStatisticDTO> statisticQuantityPost(String startDate, String endDate) {
        return repository.statisticQuantityPost(startDate, endDate);
    }

    @Override
    public Page<Post> findAllPost(Pageable pageable) {
        return repository.findAllPost(pageable);
    }
}
