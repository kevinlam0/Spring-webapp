package com.kevinlam.BlogPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blogpost")
@CrossOrigin
public class BlogPostController {

    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping("/comments")
    public void addComment(@RequestBody Comment c) {
        commentService.addComment(c);
    }

    @DeleteMapping("/comments")
    public void deleteComment(@RequestBody Map<String, String> request) {
        System.out.println("Delete function is being called");
        int id = Integer.parseInt(request.get("id"));
        String itemType = request.get("type");
        try {
            if (itemType.equals("reply")) { replyService.deleteReplyByID(id); }
            else if (itemType.equals("comment")) { commentService.deleteCommentByID(id); }
            else {throw new IllegalArgumentException("Something went wrong with deleting comment"); }
        }
        catch (Exception e ) { throw new IllegalArgumentException("Something went wrong with deleting comment"); }
    }

    @PostMapping("/comments/{comment_id}/reply")
    public void addReply(@PathVariable("comment_id") int commentId, @RequestBody Reply reply) {
        replyService.addReplyToComment(commentId, reply);
    }

    @PostMapping("/comments/{comment_id}/like")
    public void addCommentLike(@PathVariable("comment_id") int commentId) {
        commentService.incrementLikes(commentId);
    }
    @PostMapping("/comments/{comment_id}/reply/{reply_id}/like")
    public void addReplyLike(@PathVariable("reply_id") int replyID) {
        replyService.incrementLike(replyID);
    }

    @PostMapping("/comments/{comment_id}/unlike")
    public void subtractLike(@PathVariable("comment_id") int commentId) {
        commentService.decrementLikes(commentId);
    }

    @PostMapping("/comments/{comment_id}/reply/{reply_id}/unlike")
    public void subtractReplyLike(@PathVariable("reply_id") int replyID) {
        replyService.decrementLike(replyID);
    }
}
