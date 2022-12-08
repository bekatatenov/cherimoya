package com.herimoya.cherimoya.service;

import com.herimoya.cherimoya.dao.PostRepository;
import com.herimoya.cherimoya.entity.Post;
import com.herimoya.cherimoya.enums.Status;
import com.herimoya.cherimoya.enums.UsersStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
/*
    public void delete(Post id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post != null) {
            post.setPStatus(Status.Delete);
            this.postRepository.save(post);
        }
    }
*/
    public List<Post> findAllActive() {
        return postRepository.findAll();
    }

    public void update(Post post) {
    }
}
