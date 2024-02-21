package com.kevinlam.BlogPost;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ReplyDBTest {
    @Autowired
    private ReplyDB db;
    private Reply reply;

    @BeforeEach
    public void setupTestData() {
        reply = new Reply();
    }
}
