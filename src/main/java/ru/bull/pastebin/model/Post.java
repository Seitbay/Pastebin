package ru.bull.pastebin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;


@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String hash;

    @Column(nullable = false, unique = true)
    private String link;

}
