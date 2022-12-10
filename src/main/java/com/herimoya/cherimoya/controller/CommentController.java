package com.herimoya.cherimoya.controller;

import com.herimoya.cherimoya.dao.CommentRepository;
import com.herimoya.cherimoya.entity.Comment;
import com.herimoya.cherimoya.enums.Status;
import com.herimoya.cherimoya.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "comment-add";
    }

    @PostMapping(value = "/comment/add-save")
    public String postCommentAdd(@RequestParam String text, Model model) {
        Comment comment = new Comment(text);
        comment.setCreated_date_comment(new Date());
        comment.setCStatus(Status.Active);
        commentRepository.save(comment);
        return "redirect:/comment";
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
        if (!commentRepository.existsById(id)) {
            return "redirect:/comment";
        }
        Optional<Comment> comment = commentRepository.findById(id);
        ArrayList<Comment> res = new ArrayList<>();
        comment.ifPresent(res::add);
        model.addAttribute("comment", res);
        return "comment-edit";
    }

    @PostMapping(value = "/comment/edit-save")
    public String advertCommentEdit(@ModelAttribute Comment comment) {
        comment.setText(comment.getText());
        comment.setCStatus(Status.Active);
        comment.setCreated_date_comment(new Date());
        commentRepository.save(comment);
        return "redirect:/comment";
    }

    @PostMapping(value = "/comment/remove-save")
    public String postCommentDelete(@ModelAttribute Comment comment) {
        comment.setCreated_date_comment(new Date());
        commentRepository.delete(comment);
        return "redirect:/comment";
    }
}
