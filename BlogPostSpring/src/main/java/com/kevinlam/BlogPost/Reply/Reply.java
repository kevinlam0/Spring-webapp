package com.kevinlam.BlogPost.Reply;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kevinlam.BlogPost.Comment.Comment;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="comment_id")
    @JsonBackReference
    private Comment comment;

    private String content;
    private String name;
    private LocalDateTime submission;
    private int likes;

    public Reply(Comment comment, String content, String name) {
        this.comment = comment;
        this.content = content;
        this.name = name;
    }

    public Reply() {}
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Comment getComment() { return comment; }

    public void setComment(Comment comment) { this.comment = comment; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDateTime getSubmission() {
        return submission;
    }

    public void setSubmission(LocalDateTime submission) {
        this.submission = submission;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", comment=" + comment.getId() +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", submission=" + submission +
                ", likes=" + likes +
                '}';
    }
}