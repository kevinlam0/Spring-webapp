package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Comment> getCommentFromID(int id) {return db.findById(id);}
    public void deleteCommentByID(int id) { db.deleteById(id); }

    public void incrementLikes(int commentId) {
        Optional<Comment> optionalComment = db.findById(commentId);
        if (optionalComment.isEmpty()) { throw new IllegalArgumentException("Cannot increment like from backend"); }
        Comment comment = optionalComment.get();
        comment.setLikes(comment.getLikes() + 1);
        db.save(comment);
    }
}
