package com.kevinlam.BlogPost.Reply;

import com.kevinlam.BlogPost.Comment.Comment;
import com.kevinlam.BlogPost.Comment.CommentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired
    ReplyDB replyDB;
    @Autowired
    CommentDB commentDB;

    public List<Reply> getAllReplies(Comment c) {
        List<Reply> res = replyDB.findByComment(c.getId());
        res.sort(Comparator.comparing(Reply::getSubmission));
        return res;
    }
    public void addReplyToComment(int commentID, Reply r) {
        if (r.getContent().length() > 1000) {throw new IllegalArgumentException();}
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
        Optional<Reply> optionalReply = replyDB.findById(id);
        if (optionalReply.isEmpty()) { throw new IllegalArgumentException(); }
        Reply reply = optionalReply.get();

        Optional<Comment> optionalComment = commentDB.findById(reply.getComment().getId());
        if (optionalComment.isEmpty()) { throw new IllegalArgumentException(); }
        Comment comment = optionalComment.get();

        comment.getReplies().removeIf(r -> r.getId() == id);
        commentDB.save(comment);
        replyDB.delete(reply);
    }

    public void incrementLike(int replyID, String username) {
        if (username.equalsIgnoreCase("guest")) {
            throw new IllegalArgumentException();
        }
        Optional<Reply> optionalReply = replyDB.findById(replyID);
        if (optionalReply.isEmpty()) { throw new IllegalArgumentException(); }
        Reply reply = optionalReply.get();
        reply.addUserToLikedBy(username.toLowerCase());
        reply.setLikes(reply.getLikes() + 1);
        replyDB.save(reply);
    }

    public void decrementLike(int replyID, String username) {
        if (username.equalsIgnoreCase("guest")) {
            throw new IllegalArgumentException();
        }

        Optional<Reply> optionalReply = replyDB.findById(replyID);
        if (optionalReply.isEmpty()) { throw new IllegalArgumentException(); }
        Reply reply = optionalReply.get();
        reply.removeUserFromLikedBy(username.toLowerCase());
        reply.setLikes(reply.getLikes() - 1);
        replyDB.save(reply);
    }
}

