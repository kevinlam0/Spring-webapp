package com.kevinlam.BlogPost;

import com.kevinlam.BlogPost.Comment.Comment;
import com.kevinlam.BlogPost.Comment.CommentDB;
import com.kevinlam.BlogPost.Reply.Reply;
import com.kevinlam.BlogPost.Reply.ReplyDB;
import com.kevinlam.BlogPost.Reply.ReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class ReplyServiceTest {
    @Mock
    private ReplyDB mockReplyDB;
    @InjectMocks
    private ReplyService replyService;
    @Mock
    CommentDB mockCommentDB;
    @Mock
    Reply mockReply;


    @Test
    public void testAddReply_normalAddReply() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        when(mockReply.getName()).thenReturn("Kevin");
        when(mockReply.getContent()).thenReturn("Normal content");
        when(mockCommentDB.findById(1)).thenReturn(optionalComment);

        assertDoesNotThrow(() -> replyService.addReplyToComment(1, mockReply));

        verify(mockReply, times(1)).setComment(comment1);
        verify(mockReplyDB, times(1)).save(mockReply);
    }
    @Test
    public void testAddReplyToComment_guestAddReply() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        when(mockReply.getName()).thenReturn("Guest");
        lenient().when(mockReply.getContent()).thenReturn("Normal content");
        lenient().when(mockCommentDB.findById(1)).thenReturn(optionalComment);

        assertThrows(IllegalArgumentException.class, () -> replyService.addReplyToComment(1, mockReply));

        verify(mockReply, never()).setComment(comment1);
        verify(mockReplyDB, never()).save(mockReply);
    }
    @Test
    public void testAddReply_blankAddReply()  {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        lenient().when(mockReply.getName()).thenReturn("kevin");
        lenient().when(mockReply.getContent()).thenReturn("    ");
        lenient().when(mockCommentDB.findById(1)).thenReturn(optionalComment);

        assertThrows(IllegalArgumentException.class, () -> replyService.addReplyToComment(1, mockReply));

        verify(mockReply, never()).setComment(comment1);
        verify(mockReplyDB, never()).save(mockReply);
    }
    @Test
    public void testAddReply_longAddReply()  {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.of(comment1);
        lenient().when(mockReply.getName()).thenReturn("kevin");
        lenient().when(mockCommentDB.findById(1)).thenReturn(optionalComment);
        when(mockReply.getContent()).thenReturn("Lorem ipsum dolor sit amet consectetur. Proin integer faucibus a interdum nullam lacinia. " +
                "Nam id sagittis bibendum pretium id fames in luctus commodo. Odio euismod nunc eget ultrices at purus sagittis. " +
                "Sagittis eu magna purus pulvinar curabitur ornare facilisis. Proin ac pellentesque varius cras posuere mi. " +
                "Nunc vitae ullamcorper aliquet orci facilisis viverra. Facilisi scelerisque ullamcorper tincidunt sit et. " +
                "Odio vitae elementum sapien volutpat. Pellentesque tempor senectus nec mattis. Eu eu iaculis elementum ut vel sem dolor faucibus " +
                "mi. Elementum pellentesque accumsan viverra lorem. Nulla ultricies et velit fringilla dui urna laoreet porttitor consequat. Id in " +
                "nec justo duis interdum eu. Faucibus vulputate turpis enim imperdiet sit. At aliquam aliquet pretium neque faucibus adipiscing enim. " +
                "Suspendisse leo fringilla eget interdum eget feugiat semper et justo. Tincidunt fusce ut commodo porttitor ullamcorper habitant lacus " +
                "viverra imperdiet. Sollicitudin cras at neque mauris. Quis etiam volutpat commodo felis tempor sagittis at.");

        assertThrows(IllegalArgumentException.class, () -> replyService.addReplyToComment(1, mockReply));
        verify(mockReply, never()).setComment(comment1);
        verify(mockReplyDB, never()).save(mockReply);
    }
    @Test
    public void testAddReplyToComment_commentIDDoesntExist() {
        Comment comment1 = new Comment();
        Optional<Comment> optionalComment = Optional.empty();
        when(mockReply.getName()).thenReturn("kevin");
        lenient().when(mockReply.getContent()).thenReturn("Normal content");
        lenient().when(mockCommentDB.findById(1)).thenReturn(optionalComment);

        assertThrows(IllegalArgumentException.class, () -> replyService.addReplyToComment(1, mockReply));

        verify(mockReply, never()).setComment(comment1);
        verify(mockReplyDB, never()).save(mockReply);
    }


    @Test
    public void testDeleteReplyByID_normalDeleteReply() {
        Reply reply = new Reply();
        reply.setId(1);
        when(mockReplyDB.findById(1)).thenReturn(Optional.of(reply));

        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.getReplies().add(reply);
        reply.setComment(comment1);
        when(mockCommentDB.findById(reply.getComment().getId())).thenReturn(Optional.of(comment1));
        assertTrue(comment1.getReplies().contains(reply));

        replyService.deleteReplyByID(1);

        assertFalse(comment1.getReplies().contains(reply));
        verify(mockCommentDB, times(1)).save(comment1);
        verify(mockReplyDB, times(1)).delete(reply);
    }
    @Test
    public void testDeleteByID_noReplyWithID()  {
        int id = 2;
        Optional<Reply> mockOptionalReply = Mockito.mock(Optional.class);
        when(mockReplyDB.findById(id)).thenReturn(mockOptionalReply);
        when(mockOptionalReply.isEmpty()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> replyService.deleteReplyByID(id));
        verify(mockOptionalReply, never()).get();
        verify(mockCommentDB, never()).save(any());
        verify(mockReplyDB, never()).delete(any());
    }
    @Test
    public void testDeleteByID_noCommentWithID()  {
        int id = 2;
        Comment mockComment = Mockito.mock(Comment.class);
        Optional<Comment> mockOptionalComment = Mockito.mock(Optional.class);
        Optional<Reply> mockOptionalReply = Mockito.mock(Optional.class);
        lenient().when(mockOptionalReply.isEmpty()).thenReturn(false);

        when(mockReplyDB.findById(id)).thenReturn(mockOptionalReply);
        when(mockOptionalReply.get()).thenReturn( mockReply);
        when(mockReply.getComment()).thenReturn(mockComment);
        when(mockComment.getId()).thenReturn(3);
        when(mockCommentDB.findById(3)).thenReturn(mockOptionalComment);
        when(mockOptionalComment.isEmpty()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> replyService.deleteReplyByID(id));
        verify(mockOptionalComment, never()).get();
        verify(mockCommentDB, never()).save(any());
        verify(mockReplyDB, never()).delete(any());
    }

    @Test
    public void testIncrementLike() {
        Reply reply1 = new Reply();
        reply1.setLikes(3);
        int id = 1;
        when(mockReplyDB.findById(id)).thenReturn(Optional.of(reply1));
        assertFalse(reply1.getLikedBy().contains("kevin"));
        assertDoesNotThrow(() -> replyService.incrementLike(id, "KEVin"));

        assertTrue(reply1.getLikedBy().contains("kevin"));
        assertEquals(4, reply1.getLikes());
        verify(mockReplyDB, times(1)).save(reply1);
    }

    @Test
    public void testIncrementLike_guest() {
        Reply reply1 = new Reply();
        reply1.setLikes(3);
        int id = 1;
        lenient().when(mockReplyDB.findById(id)).thenReturn(Optional.of(reply1));
        assertEquals(3, reply1.getLikes());
        assertThrows(IllegalArgumentException.class, () -> replyService.incrementLike(id, "Guest"));

        assertEquals(3, reply1.getLikes());
        verify(mockReplyDB, never()).save(reply1);
    }
}
