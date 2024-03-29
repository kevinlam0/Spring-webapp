package com.kevinlam.BlogPost.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDB extends JpaRepository<Account, Integer> {
    @Query(value = "SELECT * FROM Account WHERE username=?",nativeQuery = true)
    Account findByUser(String username);
}
