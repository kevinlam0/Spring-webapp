package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
        if (c.getContent().trim().equals("")) {throw new IllegalArgumentException("Comment cannot be blank");}
        long millis=System.currentTimeMillis();
        Date current = new Date(millis);
        c.setDate(current);
        db.save(c);
    }
    public List<Comment> getCommentsFromUser(String username) {
        return db.findByName(username);
    }

}
