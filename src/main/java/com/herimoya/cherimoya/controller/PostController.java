package com.herimoya.cherimoya.controller;

import com.herimoya.cherimoya.dao.PostRepository;
import com.herimoya.cherimoya.entity.Post;
import com.herimoya.cherimoya.enums.Status;
import com.herimoya.cherimoya.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/post")
    public String postMain(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "post-main";
    }

    @GetMapping(value = "/post/add")
    public String postAdd(Model model) {
        return "post-add";
    }

    @PostMapping(value = "/post/add-save")
    public String advertPostAdd(@RequestParam String title,
                                String description,
                                int required_amount, Model model) {
        Post post = new Post(title, description, required_amount);
        post.setCreated_date_post(new Date());
        post.setPStatus(Status.Active);
        postRepository.save(post);
        return "redirect:/post";
    }

    @GetMapping(value = "/post/{id}")
    public String postDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/post";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "post-details";
    }

    @GetMapping(value = "/post/{id}/edit")
    public String postEdit(@PathVariable(value = "id") Long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/post";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "post-edit";
    }

    @PostMapping(value = "/post/edit-save")
    public String advertPostEdit(@ModelAttribute Post post) {
        post.setTitle(post.getTitle());
        post.setDescription(post.getDescription());
        post.setRequired_amount(post.getRequired_amount());
        post.setPStatus(Status.Active);
        post.setCreated_date_post(new Date());
        postRepository.save(post);
        return "redirect:/post";
    }

    @PostMapping(value = "/post/remove-save")
    public String deleteById(@ModelAttribute Post post){
        post.setCreated_date_post(new Date());
        postRepository.delete(post);
        return "redirect:/post";
    }
}
