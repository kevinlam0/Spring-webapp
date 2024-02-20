package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReplyService {
    @Autowired
    ReplyDB db;

    public List<Reply> getAllReplies(Comment c) {
        return db.findByComment(c.getId());
    }
    public void addReplyToComment(Comment c, Reply r) {
        r.setComment(c);
        long millis = System.currentTimeMillis();
        Date current = new Date(millis);
        r.setSubmission(current);
        db.save(r);
    }
    public void deleteReplyByID(int id) { db.deleteById(id);}
}

