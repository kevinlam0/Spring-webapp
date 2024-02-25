package com.kevinlam.BlogPost.Reply;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kevinlam.BlogPost.Comment.Comment;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="comment_id")
    @JsonBackReference
    private Comment comment;

    @Column(name = "liked_by", columnDefinition = "text[]")
    private List<String> likedBy;
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

    public List<String> getLikedBy() {
        if(likedBy == null) {likedBy = new ArrayList<>();}
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public void addUserToLikedBy(String username) {
        if (this.likedBy == null) { this.likedBy = new ArrayList<>(); }
        if (this.likedBy.contains(username)) {throw new IllegalArgumentException("You cannot like the comment twice");}
        this.likedBy.add(username);
    }
    public void removeUserFromLikedBy(String username) {
        if (this.likedBy == null || !this.likedBy.contains(username)) {
            throw new IllegalArgumentException("You cannot remove like if you aren't in a liked user.");
        }
        this.likedBy.remove(username);
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
