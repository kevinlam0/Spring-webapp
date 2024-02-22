package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class CommentService {
    @Autowired
    CommentDB db;
    public List<Comment> getAllComments() {
        List<Comment> res = db.findAll();
        res.sort(Comparator.comparing(Comment::getSubmission));
        Collections.reverse(res);
        for (Comment c: res) {
            c.sortRepliesByDate();
        }
        return res;
    }
    public void addComment(Comment c) {
        if (c.getContent().trim().equals("")) {throw new IllegalArgumentException("Comment cannot be blank");}
        setCurrentTime(c);
        db.save(c);
    }

    private static void setCurrentTime(Comment c) {
        long millis=System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(millis);
        ZoneId zoneId = ZoneId.systemDefault(); // Or specify a specific time zone
        LocalDateTime current = instant.atZone(zoneId).toLocalDateTime();
        c.setSubmission(current);
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
    public void decrementLikes(int commentId) {
        Optional<Comment> optionalComment = db.findById(commentId);
        if (optionalComment.isEmpty()) { throw new IllegalArgumentException("Cannot decrement like from backend"); }
        Comment comment = optionalComment.get();
        comment.setLikes(comment.getLikes() - 1);
        db.save(comment);
    }
}
