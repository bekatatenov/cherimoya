package com.cherimoya.cherimoya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "index")
    private int index;

    @Column(name = "image")
    private String image;

    @Column(name = "post_id")
    private long postId;

    @Column(name = "user_id")
    private long userId;
}
