package com.kevinlam.BlogPost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class BlogPostApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BlogPostApplication.class, args);
		CommentService service = context.getBean(CommentService.class);
		List<Comments> comments = service.getAllComments();
		for (Comments c : comments) {
			System.out.println(c);
		}
	}

}
