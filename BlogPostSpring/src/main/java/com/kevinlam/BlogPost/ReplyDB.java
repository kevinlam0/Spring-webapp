package com.kevinlam.BlogPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyDB extends JpaRepository<Reply, Integer> {
}