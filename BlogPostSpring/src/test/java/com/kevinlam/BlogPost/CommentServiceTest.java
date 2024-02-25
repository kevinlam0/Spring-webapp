package com.kevinlam.BlogPost;

import com.kevinlam.BlogPost.Comment.Comment;
import com.kevinlam.BlogPost.Comment.CommentDB;
import com.kevinlam.BlogPost.Comment.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentDB commentDB;
    @InjectMocks
    private CommentService commentService;
    @Test
    public void testGetAllCommentsDescendingOrder() {
        // Mock data
        Comment comment1 = new Comment();
        comment1.setSubmission(LocalDateTime.of(2022, 2, 20, 10, 0));
        Comment comment2 = new Comment();
        comment2.setSubmission(LocalDateTime.of(2022, 2, 21, 10, 0));
        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        when(commentDB.findAll()).thenReturn(comments);

        List<Comment> result = commentService.getAllComments();

        assertEquals(2, result.size());
        assertEquals(comment2, result.get(0));
        assertEquals(comment1, result.get(1));
    }

//    @Test
//    public void testGetAllComments() {
//        // Create sample comments
//        Comment parentComment1 = new Comment();
//        parentComment1.setId(1);
//        parentComment1.setSubmission(LocalDateTime.of(2022, 1, 1, 10, 0)); // Older date
//
//        Comment reply1 = new Comment();
//        reply1.setSubmission(LocalDateTime.of(2022, 1, 2, 10, 0)); // Later date
//        parentComment1.addReply(reply1);
//
//        Comment parentComment2 = new Comment();
//        parentComment2.setId(2);
//        parentComment2.setSubmission(LocalDateTime.of(2022, 1, 3, 10, 0)); // Later date
//
//        Comment reply2 = new Comment();
//        reply2.setSubmission(LocalDateTime.of(2022, 1, 3, 11, 0)); // Later time
//        parentComment2.addReply(reply2);
//
//        List<Comment> comments = new ArrayList<>();
//        comments.add(parentComment1);
//        comments.add(parentComment2);
//
//        // Mock behavior of the CommentDB
//        when(commentDB.findAll()).thenReturn(comments);
//
//        // Call the method under test
//        List<Comment> result = commentService.getAllComments();
//
//        // Assertions
//        assertEquals(2, result.size());
//        assertEquals(2, result.get(0).getId()); // Check if the second comment comes first
//        assertEquals(1, result.get(1).getId()); // Check if the first comment comes second
//
//        // Check if replies are sorted in ascending order by date
//        assertEquals(1, result.get(0).getReplies().get(0).getId()); // Check reply order for first comment
//        assertEquals(2, result.get(1).getReplies().get(0).getId()); // Check reply order for second comment
//    }
}
