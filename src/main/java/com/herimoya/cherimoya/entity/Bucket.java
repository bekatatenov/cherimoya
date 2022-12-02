package com.herimoya.cherimoya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "buckets")
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bucket_id_seq")
    @SequenceGenerator(name = "bucket_id_seq", sequenceName = "bucket_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "buckets_posts",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> posts;
}
