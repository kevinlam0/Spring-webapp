package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @PostMapping("/comments")
    public void addComment(@RequestBody Comment c) { service.addComment(c); }
}
