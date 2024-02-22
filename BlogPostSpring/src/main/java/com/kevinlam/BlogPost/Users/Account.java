package com.kevinlam.BlogPost.Users;

import jakarta.persistence.*;

@Entity
@Table(name="Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String userLower;
    public Account() {}
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Account(String username, String password, String userLower) {
        this.username = username;
        this.password = password;
        this.userLower = userLower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserLower() {
        return userLower;
    }

    public void setUserLower(String userLower) {
        this.userLower = userLower;
    }
}
