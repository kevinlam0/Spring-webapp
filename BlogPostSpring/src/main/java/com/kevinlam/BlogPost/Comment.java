package com.kevinlam.BlogPost;

import jakarta.persistence.*;

import java.sql.Date;
@Table(name = "Comment")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private Date submission;
    private int likes;

    private int dislikes;
    private String name;

    public Comment() { }

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + content + '\'' +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", submission=" + submission +
                '}';
    }

    public Comment(String comment, String name, Date date) {
        this.content = comment;
        this.submission = date;
        this.name = name;
        this.likes = 0;
        this.dislikes = 0;
    }
    public Comment(String comment, String name, Date submission, int likes, int dislikes) {
        this.content = comment;
        this.submission = submission;
        this.name = name;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getContent() {
        return content;
    }

    public void content(String content) {
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

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
