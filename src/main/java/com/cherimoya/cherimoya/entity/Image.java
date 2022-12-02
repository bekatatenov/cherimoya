package com.cherimoya.cherimoya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "")

    @Type(type = "org.hibernate.type.BinaryType")
    @Lob
    @Column
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;


//    @ManyToOne
//    private Post post;
}
