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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

//    @Test
//    public void testAddComment_guestAddComment() {
//        when(mockComment1.getName()).thenReturn("guESt");
//        when(mockComment1.getContent()).thenReturn("Normal content");
//        assertThrows(Exception.class, () -> {commentService.addComment(mockComment1);});
//        verify(commentDB, never()).save(mockComment1);
//    }
//
//    @Test
//    public void testAddComment_blankAddComment()  {
//        when(mockComment1.getName()).thenReturn("kevin");
//        when(mockComment1.getContent()).thenReturn("    ");
//        assertThrows(Exception.class, () -> {commentService.addComment(mockComment1);});
//        verify(commentDB, never()).save(mockComment1);
//    }
//
//    @Test
//    public void testAddComment_longAddComment()  {
//        lenient().when(mockComment1.getName()).thenReturn("kevin");
//        when(mockComment1.getContent()).thenReturn("Lorem ipsum dolor sit amet consectetur. Proin integer faucibus a interdum nullam lacinia. " +
//                "Nam id sagittis bibendum pretium id fames in luctus commodo. Odio euismod nunc eget ultrices at purus sagittis. " +
//                "Sagittis eu magna purus pulvinar curabitur ornare facilisis. Proin ac pellentesque varius cras posuere mi. " +
//                "Nunc vitae ullamcorper aliquet orci facilisis viverra. Facilisi scelerisque ullamcorper tincidunt sit et. " +
//                "Odio vitae elementum sapien volutpat. Pellentesque tempor senectus nec mattis. Eu eu iaculis elementum ut vel sem dolor faucibus " +
//                "mi. Elementum pellentesque accumsan viverra lorem. Nulla ultricies et velit fringilla dui urna laoreet porttitor consequat. Id in " +
//                "nec justo duis interdum eu. Faucibus vulputate turpis enim imperdiet sit. At aliquam aliquet pretium neque faucibus adipiscing enim. " +
//                "Suspendisse leo fringilla eget interdum eget feugiat semper et justo. Tincidunt fusce ut commodo porttitor ullamcorper habitant lacus " +
//                "viverra imperdiet. Sollicitudin cras at neque mauris. Quis etiam volutpat commodo felis tempor sagittis at.");
//
//        assertThrows(IllegalArgumentException.class, () -> {commentService.addComment(mockComment1);});
//        verify(commentDB, never()).save(mockComment1);
//    }
}
