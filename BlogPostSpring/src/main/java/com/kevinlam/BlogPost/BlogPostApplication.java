package com.kevinlam.BlogPost;

import com.kevinlam.BlogPost.Users.Account;
import com.kevinlam.BlogPost.Users.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.util.List;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class BlogPostApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BlogPostApplication.class, args);
//		AccountService service = context.getBean(AccountService.class);
//		Account account = new Account("ke$inlam021", "paSsword");
//		service.registerAccount(account);
//		System.out.println(service.checkLoginCredentials("Ke$inlam021", "paSsword"));
//		CommentService commentService = context.getBean(CommentService.class);
//		Comment comment = commentService.getCommentFromID(1).get();
//		List<Reply> replies = comment.getReplies();
//		for (Reply reply: replies) {
//			System.out.println(reply);
//		}
//		Reply reply = new Reply(comment, "This is the first reply", "Kevin");
//		service.addReplyToComment(comment, reply);
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
