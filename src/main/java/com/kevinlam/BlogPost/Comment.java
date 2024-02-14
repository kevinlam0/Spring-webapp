package com.kevinlam.BlogPost;

import java.sql.Date;

public class Comment {
    private String comment;
    private Date date;
    private int likes;

    private int dislikes;
    private String name;

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", date=" + date +
                '}';
    }

    public Comment(String comment, String name, Date date) {
        this.comment = comment;
        this.date = date;
        this.name = name;
        this.likes = 0;
        this.dislikes = 0;
    }
    public Comment(String comment, String name, Date date, int likes, int dislikes) {
        this.comment = comment;
        this.date = date;
        this.name = name;
        this.likes = likes;
        this.dislikes = dislikes;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
