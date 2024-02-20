package com.kevinlam.BlogPost;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Table(name = "Comment")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private Date submission;
    private int likes;

    private String name;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Reply> replies;

    public Comment() { }

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + content + '\'' +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", submission=" + submission +
                '}';
    }

    public Comment(String comment, String name, Date date) {
        this.content = comment;
        this.submission = date;
        this.name = name;
        this.likes = 0;
    }
    public Comment(String comment, String name, Date submission, int likes) {
        this.content = comment;
        this.submission = submission;
        this.name = name;
        this.likes = likes;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return submission;
    }

    public void setDate(Date date) {
        this.submission = date;
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

    public Date getSubmission() {
        return submission;
    }

    public void setSubmission(Date submission) {
        this.submission = submission;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
