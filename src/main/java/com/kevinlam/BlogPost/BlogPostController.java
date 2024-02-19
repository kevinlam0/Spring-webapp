package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blogpost")
@CrossOrigin
public class BlogPostController {

    @Autowired
    CommentService service;

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return service.getAllComments();
    }
}
