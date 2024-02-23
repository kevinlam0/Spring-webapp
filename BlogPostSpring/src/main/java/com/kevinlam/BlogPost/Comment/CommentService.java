package com.kevinlam.BlogPost.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
    public void addComment(Comment c) throws Exception {
        if (c.getName().equalsIgnoreCase("guest")) {throw new Exception();}
        if (c.getContent().trim().equals("")) { throw new Exception(); }
        setCurrentTime(c);
        db.save(c);
    }
    public void deleteCommentByID(int id) throws EmptyResultDataAccessException {
        db.deleteById(id);
    }
    public void incrementLikes(int commentId, String username) {
        if (username.equalsIgnoreCase("guest")) {
            throw new IllegalArgumentException();
        }

        Optional<Comment> optionalComment = db.findById(commentId);
        if (optionalComment.isEmpty()) { throw new IllegalArgumentException(); }
        Comment comment = optionalComment.get();
        comment.addUserToLikedBy(username.toLowerCase());
        comment.setLikes(comment.getLikes() + 1);
        db.save(comment);
    }
    public void decrementLikes(int commentId, String username) {
        if (username.equalsIgnoreCase("guest")) {
            throw new IllegalArgumentException("");
        }

        Optional<Comment> optionalComment = db.findById(commentId);
        if (optionalComment.isEmpty()) { throw new IllegalArgumentException(""); }
        Comment comment = optionalComment.get();
        comment.removeUserFromLikedBy(username.toLowerCase());
        comment.setLikes(comment.getLikes() - 1);
        db.save(comment);
    }
    private static void setCurrentTime(Comment c) {
        long millis=System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(millis);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime current = instant.atZone(zoneId).toLocalDateTime();
        c.setSubmission(current);
    }
}
