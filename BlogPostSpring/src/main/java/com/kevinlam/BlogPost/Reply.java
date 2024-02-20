package com.kevinlam.BlogPost;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name= "id")
    private Comment comment;

    private String content;
    private String name;
    private Date submission;
    private int likes;
}
