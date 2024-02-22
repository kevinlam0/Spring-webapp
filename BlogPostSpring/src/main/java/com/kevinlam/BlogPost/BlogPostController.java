package com.kevinlam.BlogPost;

import com.kevinlam.BlogPost.Comment.Comment;
import com.kevinlam.BlogPost.Comment.CommentService;
import com.kevinlam.BlogPost.Reply.Reply;
import com.kevinlam.BlogPost.Reply.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/comments/{username}")
    public void addComment(@RequestBody Comment c, @PathVariable("username") String username ) {
        commentService.addComment(c, username);
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

    @PostMapping("/comments/{comment_id}/like/{username}")
    public void addCommentLike(@PathVariable("comment_id") int commentId, @PathVariable("username") String username) {
        commentService.incrementLikes(commentId, username);
    }

    @PostMapping("/comments/{comment_id}/unlike/{username}")
    public void subtractLike(@PathVariable("comment_id") int commentId, @PathVariable("username") String username) {
        commentService.decrementLikes(commentId, username);
    }

    @PostMapping("/comments/{comment_id}/reply/{reply_id}/like/{username}")
    public void addReplyLike(@PathVariable("reply_id") int replyID, @PathVariable("username") String username) {
        replyService.incrementLike(replyID, username);
    }
    @PostMapping("/comments/{comment_id}/reply/{reply_id}/unlike/{username}")
    public void subtractReplyLike(@PathVariable("reply_id") int replyID, @PathVariable("username") String username) {
        replyService.decrementLike(replyID, username);
    }
}
