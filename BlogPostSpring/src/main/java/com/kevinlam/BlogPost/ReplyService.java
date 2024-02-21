package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired
    ReplyDB db;
    @Autowired
    CommentDB commentDB;

    public List<Reply> getAllReplies(Comment c) {
        return db.findByComment(c.getId());
    }
    public void addReplyToComment(int commentID, Reply r) {
        Optional<Comment> optionalComment = commentDB.findById(commentID);
        if (optionalComment.isPresent()) {
            Comment c = optionalComment.get();
            r.setComment(c);
            long millis = System.currentTimeMillis();
            Date current = new Date(millis);
            r.setSubmission(current);
            db.save(r);
        }
        else { throw new IllegalArgumentException("There is not a corresponding comment to this reply: "); }
    }
    public void deleteReplyByID(int id) { db.deleteById(id);}
}

