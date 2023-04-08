package com.moon.hakjumbank.repository;

import com.moon.hakjumbank.domain.Member;
import com.moon.hakjumbank.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *  DB와 통신하는 역할 xml과 비슷
 * */
@Repository
@RequiredArgsConstructor // final 있는 애 생성자를 만들어줌
public class PostRepository {

    private final EntityManager em;

    // 글작성하기
    public void writPost(Post post) {
        em.persist(post);
    }

    // 글 찾기
    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }


}
