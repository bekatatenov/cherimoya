package com.herimoya.cherimoya.service;

import com.Project.Post2.dao.CommentRepository;
import com.Project.Post2.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private List<Comment> getAllPost() {
        return this.commentRepository.findAll();
    }

}
