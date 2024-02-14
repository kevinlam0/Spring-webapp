package com.kevinlam.BlogPost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDB {
    Connection conn = null;

    public CommentDB() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:1234/kevin", "postgres", "0000");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addComment(Comment comment) {
        String query = "INSERT INTO Comments (comment, name, likes, dislikes, submission) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, comment.getComment());
            st.setString(2, comment.getName());
            st.setInt(3, comment.getLikes());
            st.setInt(4, comment.getDislikes());
            st.setDate(5, comment.getDate());
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
