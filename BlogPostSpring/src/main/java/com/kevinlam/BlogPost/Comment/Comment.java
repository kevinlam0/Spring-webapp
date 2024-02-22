package com.kevinlam.BlogPost.Comment;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kevinlam.BlogPost.Reply.Reply;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Table(name = "Comment")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private LocalDateTime submission;
    private int likes;

    @Column(name = "liked_by", columnDefinition = "text[]")
    private List<String> likedBy;
    private String name;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Reply> replies;

    public Comment() { }
    public Comment(String comment, String name, LocalDateTime date) {
        this.content = comment;
        this.submission = date;
        this.name = name;
        this.likes = 0;
    }
    public Comment(String comment, String name, LocalDateTime submission, int likes) {
        this.content = comment;
        this.submission = submission;
        this.name = name;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + content + '\'' +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", submission=" + submission +
                '}';
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getSubmission() {
        return submission;
    }

    public void setSubmission(LocalDateTime submission) {
        this.submission = submission;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
    public void sortRepliesByDate() {this.replies.sort(Comparator.comparing(Reply::getSubmission));}

    public List<String> getLikedBy() {
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
}
