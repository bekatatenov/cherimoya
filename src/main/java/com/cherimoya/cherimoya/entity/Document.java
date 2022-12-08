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

    @Column(name = "document")
    private String document;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DocumentStatus secretStatus;

//    @JoinColumn(name = "userID")
//    @ManyToOne
//    @MapsId
//    private User user;

    @Column(name = "type")
    private String type;
}
