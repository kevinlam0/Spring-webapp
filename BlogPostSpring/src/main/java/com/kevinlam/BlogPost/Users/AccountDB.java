package com.kevinlam.BlogPost.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDB extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
