package com.kevinlam.BlogPost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class BlogPostApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BlogPostApplication.class, args);
		ReplyService service = context.getBean(ReplyService.class);
		CommentService commentService = context.getBean(CommentService.class);
//		Reply reply = new Reply(comment, "This is the first reply", "Kevin");
////		service.addReplyToComment(comment, reply);
//		commentService.deleteCommentByID(3);

//		List<Comment> comments = service.getAllComments();
//		for (Comment c : comments) {
//			System.out.println(c);
//		}
//		long millis=System.currentTimeMillis();
//		Date current = new Date(millis);
//		Comment c = new Comment("different comment", "Kelly", current);
//		service.addComment(c);
//		List<Comment> comments = service.getAllComments();
//		for (Comment d : comments) {
//			System.out.println(d);
//		}
	}

}
