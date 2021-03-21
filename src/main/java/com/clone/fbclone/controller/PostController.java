package com.clone.fbclone.controller;

import com.clone.fbclone.entities.PostEntity;
import com.clone.fbclone.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepo repo;

    @GetMapping("get-post")
    public List<PostEntity> getAllPosts() {
        return repo.findAll();
    }

    @PostMapping("create-post")
    public PostEntity createPosts(@RequestBody PostEntity posts) {
        return repo.save(posts);
    }

}
