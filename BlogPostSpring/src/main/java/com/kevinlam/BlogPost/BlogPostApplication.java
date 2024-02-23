package com.kevinlam.BlogPost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class BlogPostApplication {
	public static void main(String[] args) { SpringApplication.run(BlogPostApplication.class, args); }
}
