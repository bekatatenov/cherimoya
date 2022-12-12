package com.herimoya.cherimoya.entity;

import com.herimoya.cherimoya.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "posts")
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "post_id_seq")
    @SequenceGenerator(name = "post_id_seq", sequenceName = "post_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Required_Amount_Money")
    private int required_amount;

    @Column(name = "Views")
    private int views;

    @Column(name = "Created_date_post")
    private Date created_date_post;

    @Column
    @Enumerated(EnumType.STRING)
    private Status pStatus;

    public Post(String title, String description, int required_amount){
        this.title = title;
        this.description = description;
        this.required_amount = required_amount;
    }

    public Post(String title, String description, int required_amount, int views, Date created_date_post) {
        this.title = title;
        this.description = description;
        this.required_amount = required_amount;
        this.views = views;
        this.created_date_post = created_date_post;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}