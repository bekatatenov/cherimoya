package com.herimoya.cherimoya.controller;

import com.Project.Post2.dao.CommentRepository;
import com.Project.Post2.entity.Comment;
import com.Project.Post2.enums.Status;
import com.Project.Post2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/comment")
    public String commentMain(Model model) {
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "comment-main";
    }

    @GetMapping(value = "/comment/add")
    public String commentAdd(Model model) {
        return "comment-main";
    }

    @PostMapping(value = "/comment/add-save")
    public String postCommentAdd(@RequestParam String text, Model model) {
        Comment comment = new Comment(text);
        comment.setCreated_date_comment(new Date());
        comment.setCStatus(Status.Active);
        commentRepository.save(comment);
        return "comment-main";
    }

    @GetMapping(value = "/comment/{id}")
    public String commentDetails(@PathVariable(value = "id") long id, Model model) {
        if (!commentRepository.existsById(id)) {
            return "redirect:/comment";
        }
        Optional<Comment> comment = commentRepository.findById(id);
        ArrayList<Comment> res = new ArrayList<>();
        comment.ifPresent(res::add);
        model.addAttribute("comment", res);
        return "comment-details";
    }

    @GetMapping(value = "/comment/{id}/edit")
    public String commentEdit(@PathVariable(value = "id") Long id, Model model) {
        if (commentRepository.existsById(id)) {
            return "redirect:/comment";
        }
        Optional<Comment> comment = commentRepository.findById(id);
        ArrayList<Comment> res = new ArrayList<>();
        comment.ifPresent(res::add);
        model.addAttribute("comment", res);
        return "comment-edit";
    }

    @PostMapping(value = "comment/{id}/edit")
    public String advertCommentEdit(@PathVariable(value = "id") Long id, @RequestParam String text, Model model) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.setText(text);
        comment.setCreated_date_comment(new Date());
        comment.setCStatus(Status.Active);
        commentRepository.save(comment);
        return "redirect:/comment";
    }

    @PostMapping(value = "comment/{id}/remove")
    public String postCommentDelete(@PathVariable(value = "id") Long id, Model model) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.setCreated_date_comment(new Date());
        comment.setCStatus(Status.Delete);
        commentRepository.save(comment);
        return "redirect:/comment";
    }
}
