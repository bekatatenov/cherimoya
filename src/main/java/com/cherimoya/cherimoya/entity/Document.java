package com.cherimoya.cherimoya.entity;

import com.cherimoya.cherimoya.enums.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private DocumentStatus secretStatus;

    @Column(name = "userID")
    private long userID;

    @Column(name = "type")
    private String type;

    @Column(name="text")
    private String text;
}
