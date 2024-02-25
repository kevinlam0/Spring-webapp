package com.kevinlam.BlogPost;

import com.kevinlam.BlogPost.Comment.Comment;
import com.kevinlam.BlogPost.Comment.CommentService;
import com.kevinlam.BlogPost.Reply.Reply;
import com.kevinlam.BlogPost.Reply.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addComment(@RequestBody Comment c ) {
        try { commentService.addComment(c); }
        catch (IllegalArgumentException e) { return ResponseEntity.status(464).body("");}
        catch (Exception e) { return ResponseEntity.status(460).body(""); }
        return ResponseEntity.ok("");
    }

    @PostMapping("/comments/{comment_id}/reply")
    public ResponseEntity<?> addReply(@PathVariable("comment_id") int commentId, @RequestBody Reply reply) {
        try {replyService.addReplyToComment(commentId, reply);}
        catch (IllegalArgumentException e) { return ResponseEntity.status(464).body("");}
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/comments")
    public ResponseEntity<?> deleteItem(@RequestBody Map<String, String> request) {
        int id = Integer.parseInt(request.get("id"));
        String itemType = request.get("type");
        try {
            if (itemType.equals("reply")) { replyService.deleteReplyByID(id); }
            else if (itemType.equals("comment")) { commentService.deleteCommentByID(id); }
            else { ResponseEntity.status(461).body(""); }
        }
        catch (EmptyResultDataAccessException | IllegalArgumentException e ) {
            return ResponseEntity.status(462).body("");
        }
        return ResponseEntity.ok("");
    }

    @PostMapping("/comments/{comment_id}/like/{username}")
    public ResponseEntity<?> addCommentLike(@PathVariable("comment_id") int commentId, @PathVariable("username") String username) {
        try {commentService.incrementLikes(commentId, username);}
        catch (IllegalArgumentException e) { return ResponseEntity.status(463).body(""); }
        return ResponseEntity.ok("");
    }

    @PostMapping("/comments/{comment_id}/unlike/{username}")
    public ResponseEntity<?> subtractLike(@PathVariable("comment_id") int commentId, @PathVariable("username") String username) {
        try {commentService.decrementLikes(commentId, username);}
        catch (IllegalArgumentException e) { return ResponseEntity.status(463).body(""); }
        return ResponseEntity.ok("");
    }

    @PostMapping("/comments/reply/{reply_id}/like/{username}")
    public ResponseEntity<?> addReplyLike(@PathVariable("reply_id") int replyID, @PathVariable("username") String username) {
        try {replyService.incrementLike(replyID, username);}
        catch (IllegalArgumentException e) { return ResponseEntity.status(463).body(""); }
        return ResponseEntity.ok("");
    }
    @PostMapping("/comments/reply/{reply_id}/unlike/{username}")
    public ResponseEntity<?> subtractReplyLike(@PathVariable("reply_id") int replyID, @PathVariable("username") String username) {
        try {replyService.decrementLike(replyID, username);}
        catch (IllegalArgumentException e) { return ResponseEntity.status(463).body(""); }
        return ResponseEntity.ok("");
    }
}
