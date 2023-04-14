package com.moon.hakjumbank.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "posts")
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long pId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    // Constructors, getters and setters
    public static Post createPost(Member member, String title, String content) {
        Post post = new Post();
        post.setMember(member);
        post.setTitle(title);
        post.setContent(content);
        return post;

    }

}

