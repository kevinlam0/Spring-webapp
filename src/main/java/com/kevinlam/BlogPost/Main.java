package com.kevinlam.BlogPost;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        long millis=System.currentTimeMillis();
        Date current = new Date(millis);
        Comment c = new Comment("This is the second comment", current, "Kevin");
        CommentDB db = new CommentDB();
        db.addComment(c);
    }
}