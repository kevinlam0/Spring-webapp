package com.kevinlam.BlogPost;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface CommentDB extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT * FROM Comment WHERE name=?",nativeQuery = true)
    public List<Comment> findByName(String username);

//    Connection conn = null;
//
//    public CommentDB() {
//        try {
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost:1234/kevin", "postgres", "0000");
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void addComment(Comment comment) {
//        String query = "INSERT INTO Comments (comment, name, likes, dislikes, submission) values (?, ?, ?, ?, ?)";
//        try {
//            PreparedStatement st = conn.prepareStatement(query);
//            st.setString(1, comment.getComment());
//            st.setString(2, comment.getName());
//            st.setInt(3, comment.getLikes());
//            st.setInt(4, comment.getDislikes());
//            st.setDate(5, comment.getDate());
//            st.executeUpdate();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Comment> getAll() {
//        List<Comment> comments = new ArrayList<>();
//        String query = "SELECT comment, name, likes, dislikes, submission FROM Comments;";
//        try {
//            PreparedStatement st = conn.prepareStatement(query);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                comments.add(new Comment(rs.getString(1), rs.getString(2), rs.getDate(5), rs.getInt(3), rs.getInt(4)));
//            }
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return comments;
//    }
}
