package com.kevinlam.BlogPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyDB extends JpaRepository<Reply, Integer> {
    @Query(value = "SELECT * FROM Reply WHERE comment_id=?",nativeQuery = true)
    public List<Reply> findByComment(int id);
}