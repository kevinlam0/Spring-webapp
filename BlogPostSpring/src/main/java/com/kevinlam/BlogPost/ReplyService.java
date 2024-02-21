package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired
    ReplyDB replyDB;
    @Autowired
    CommentDB commentDB;

    public List<Reply> getAllReplies(Comment c) {
        return replyDB.findByComment(c.getId());
    }
    public void addReplyToComment(int commentID, Reply r) {
        Optional<Comment> optionalComment = commentDB.findById(commentID);
        if (optionalComment.isPresent()) {
            Comment c = optionalComment.get();
            r.setComment(c);
            setCurrentTime(r);
            replyDB.save(r);
        }
        else { throw new IllegalArgumentException("There is not a corresponding comment to this reply: "); }
    }

    private static void setCurrentTime(Reply r) {
        long millis = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(millis);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime current = instant.atZone(zoneId).toLocalDateTime();
        r.setSubmission(current);
    }

    public void deleteReplyByID(int id) {
        Reply reply = replyDB.findById(id).get();
        Comment comment = commentDB.findById(reply.getComment().getId()).get();
        comment.getReplies().removeIf(r -> r.getId() == id);
        commentDB.save(comment);
        replyDB.delete(reply);
    }
}

