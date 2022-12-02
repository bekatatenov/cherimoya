package com.herimoya.cherimoya.service;

import com.Project.Post2.dao.PostRepository;
import com.Project.Post2.entity.Post;
import com.Project.Post2.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllByTitle(String title) {
        return this.postRepository.findAllByTitle(title);
    }
    public void update(Long id) {
        Post post = this.postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setPStatus(Status.Delete);
            this.postRepository.save(post);
        }
    }

    public List<Post> findAllActive() {
        return postRepository.findAll();
    }
}
