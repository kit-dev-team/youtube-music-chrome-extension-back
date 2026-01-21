package kr.ac.kumoh.s20210041.example.yt_music_extension_back.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private  String name;

    @Column
    private String picture;

    @Column(nullable = false)
    private String googleSubId;

    @Builder
    public User(String email, String name, String picture, String googleSubId) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.googleSubId = googleSubId;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }
}
