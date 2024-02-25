package com.kevinlam.BlogPost;

import com.kevinlam.BlogPost.Comment.Comment;
import com.kevinlam.BlogPost.Comment.CommentDB;
import com.kevinlam.BlogPost.Comment.CommentService;
import com.kevinlam.BlogPost.Reply.Reply;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentDB commentDB;
    @InjectMocks
    private CommentService commentService;
    @Mock
    Comment mockComment1;
    @Test
    public void testGetAllComments_DescendingOrder() {
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

    @Test
    public void testGetAllComments_AndRepliesOrder() {
        // Create sample comments
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setSubmission(LocalDateTime.of(2022, 1, 1, 10, 0)); // Older date
        Comment mockComment2 = Mockito.mock(Comment.class);
        // Comment 2 setup
        when(mockComment2.getId()).thenReturn(2);
        when(mockComment2.getSubmission()).thenReturn(LocalDateTime.of(2022, 1, 3, 10, 0)); // later

        Reply reply1 = new Reply();
        reply1.setId(1);
        reply1.setSubmission(LocalDateTime.of(2022, 1, 2, 10, 0)); // earlier

        Reply reply2 = new Reply();
        reply2.setId(2);
        reply2.setSubmission(LocalDateTime.of(2022, 1, 3, 11, 0)); // later

        List<Reply> replies = new ArrayList<>();
        replies.add(reply2);
        replies.add(reply1);
        comment1.setReplies(replies);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(mockComment2);

        // Mock behavior of the CommentDB
        when(commentDB.findAll()).thenReturn(comments);

        // Call the method under test
        List<Comment> result = commentService.getAllComments();

        // Assertions
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getId()); // Check if the second comment comes first
        assertEquals(1, result.get(1).getId()); // Check if the first comment comes second

        // Check if replies are sorted in ascending order by date
        // Check reply order of the first comment
        assertEquals(1, result.get(1).getReplies().get(0).getId());
        assertEquals(2, result.get(1).getReplies().get(1).getId());
    }

    @Test
    public void testGetAllComments_NoComments() {
        List<Comment> comments = new ArrayList<>();
        when(commentDB.findAll()).thenReturn(comments);
        List<Comment> resComments = commentService.getAllComments();
        assertEquals(0, resComments.size());
    }

    @Test
    public void testAddComment_normalAddComment() {
        when(mockComment1.getName()).thenReturn("Kevin");
        when(mockComment1.getContent()).thenReturn("Normal content");
        assertDoesNotThrow(() -> commentService.addComment(mockComment1));
        verify(commentDB, times(1)).save(mockComment1);
    }

    @Test
    public void testAddComment_guestAddComment() {
        when(mockComment1.getName()).thenReturn("guESt");
        when(mockComment1.getContent()).thenReturn("Normal content");
        assertThrows(Exception.class, () -> {commentService.addComment(mockComment1);});
        verify(commentDB, never()).save(mockComment1);
    }

    @Test
    public void testAddComment_blankAddComment()  {
        when(mockComment1.getName()).thenReturn("kevin");
        when(mockComment1.getContent()).thenReturn("    ");
        assertThrows(Exception.class, () -> {commentService.addComment(mockComment1);});
        verify(commentDB, never()).save(mockComment1);
    }

    @Test
    public void testAddComment_longAddComment()  {
        lenient().when(mockComment1.getName()).thenReturn("kevin");
        when(mockComment1.getContent()).thenReturn("Lorem ipsum dolor sit amet consectetur. Proin integer faucibus a interdum nullam lacinia. " +
                "Nam id sagittis bibendum pretium id fames in luctus commodo. Odio euismod nunc eget ultrices at purus sagittis. " +
                "Sagittis eu magna purus pulvinar curabitur ornare facilisis. Proin ac pellentesque varius cras posuere mi. " +
                "Nunc vitae ullamcorper aliquet orci facilisis viverra. Facilisi scelerisque ullamcorper tincidunt sit et. " +
                "Odio vitae elementum sapien volutpat. Pellentesque tempor senectus nec mattis. Eu eu iaculis elementum ut vel sem dolor faucibus " +
                "mi. Elementum pellentesque accumsan viverra lorem. Nulla ultricies et velit fringilla dui urna laoreet porttitor consequat. Id in " +
                "nec justo duis interdum eu. Faucibus vulputate turpis enim imperdiet sit. At aliquam aliquet pretium neque faucibus adipiscing enim. " +
                "Suspendisse leo fringilla eget interdum eget feugiat semper et justo. Tincidunt fusce ut commodo porttitor ullamcorper habitant lacus " +
                "viverra imperdiet. Sollicitudin cras at neque mauris. Quis etiam volutpat commodo felis tempor sagittis at.");

        assertThrows(IllegalArgumentException.class, () -> {commentService.addComment(mockComment1);});
        verify(commentDB, never()).save(mockComment1);
    }

    @Test
    public void testIncrementLike() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        List<String> likedBy = Mockito.mock(ArrayList.class);
        comment1.setLikedBy(likedBy);
        comment1.setLikes(4);

        when(commentDB.findById(1)).thenReturn(optionalComment);
        assertDoesNotThrow(() -> commentService.incrementLikes(1, "kevin"));
        verify(comment1.getLikedBy(), times(1)).add("kevin");
        assertEquals(5, comment1.getLikes());
        verify(commentDB, times(1)).save(comment1);
    }
    @Test
    public void testIncrementLike_guest() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        List<String> likedBy = Mockito.mock(ArrayList.class);
        comment1.setLikedBy(likedBy);
        comment1.setLikes(4);

        lenient().when(commentDB.findById(1)).thenReturn(optionalComment);
        assertThrows(IllegalArgumentException.class, () -> commentService.incrementLikes(1, "Guest"));
        verify(comment1.getLikedBy(), never()).add("guest");
        assertEquals(4, comment1.getLikes());
        verify(commentDB, never()).save(comment1);
    }

    @Test
    public void testIncrementLike_idNotValid() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.empty();
        List<String> likedBy = Mockito.mock(ArrayList.class);
        comment1.setLikedBy(likedBy);
        comment1.setLikes(4);

        lenient().when(commentDB.findById(1)).thenReturn(optionalComment);
        assertThrows(IllegalArgumentException.class, () -> commentService.incrementLikes(1, "kevin"));
        verify(comment1.getLikedBy(), never()).add("kevin");
        assertEquals(4, comment1.getLikes());
        verify(commentDB, never()).save(comment1);
    }

    @Test
    public void testDecrementLike() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        List<String> likedBy = Mockito.mock(ArrayList.class);
        comment1.setLikedBy(likedBy);
        comment1.setLikes(4);

        when(commentDB.findById(1)).thenReturn(optionalComment);
        when(likedBy.contains("kevin")).thenReturn(true);
        assertDoesNotThrow(() -> commentService.decrementLikes(1, "KEvin"));
        verify(comment1.getLikedBy(), times(1)).remove("kevin");
        assertEquals(3, comment1.getLikes());
        verify(commentDB, times(1)).save(comment1);
    }

    @Test
    public void testDecrementLike_guest() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        List<String> likedBy = Mockito.mock(ArrayList.class);
        comment1.setLikedBy(likedBy);
        comment1.setLikes(4);

        lenient().when(commentDB.findById(1)).thenReturn(optionalComment);
        lenient().when(likedBy.contains("guest")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> commentService.decrementLikes(1, "Guest"));
        verify(comment1.getLikedBy(), never()).remove("guest");
        assertEquals(4, comment1.getLikes());
        verify(commentDB, never()).save(comment1);
    }

    @Test
    public void testDecrementLike_idNotValid() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.empty();
        List<String> likedBy = Mockito.mock(ArrayList.class);
        comment1.setLikedBy(likedBy);
        comment1.setLikes(4);

        lenient().when(commentDB.findById(1)).thenReturn(optionalComment);
        lenient().when(likedBy.contains("kevin")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> commentService.decrementLikes(1, "kevin"));
        verify(comment1.getLikedBy(), never()).remove("kevin");
        assertEquals(4, comment1.getLikes());
        verify(commentDB, never()).save(comment1);
    }
}
