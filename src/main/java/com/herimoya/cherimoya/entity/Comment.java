package com.herimoya.cherimoya.entity;

import com.herimoya.cherimoya.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "comment_id_seq")
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "Text")
    private String text;

    @Column(name = "Created_date_comment")
    private Date created_date_comment;

    @Column
    @Enumerated(EnumType.STRING)
    private Status cStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    public Comment(String text) {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", created_date_comment=" + created_date_comment +
                ", cStatus=" + cStatus +
                ", user=" + user +
                '}';
    }
}
