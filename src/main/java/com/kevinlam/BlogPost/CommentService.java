package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDB db;
    public List<Comment> getAllComments() {
        return db.findAll();
    }
    public void addComment(Comment c) {
        db.save(c);
    }
    public List<Comment> getCommentsFromUser(String username) {
        return db.findByName(username);
    }
//    public List<Comment> getCommentsOfUser(String username) {
//        // TODO: stub
//        return new ArrayList<>();
//    }
}
