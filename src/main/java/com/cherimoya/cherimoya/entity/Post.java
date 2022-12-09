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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToMany(targetEntity = Image.class, mappedBy = "image", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Long id;

    @ManyToOne
    private User user;
}
