package com.herimoya.cherimoya.service;

import com.herimoya.cherimoya.dao.PostRepository;
import com.herimoya.cherimoya.entity.Post;
import com.herimoya.cherimoya.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAllActive() {
        return postRepository.findAll();
    }

    public void delete(Long id) {
        Post post = this.postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setPStatus(Status.Delete);
            this.postRepository.save(post);
        }
    }
}