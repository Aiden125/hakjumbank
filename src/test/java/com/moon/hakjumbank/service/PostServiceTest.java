package com.moon.hakjumbank.service;

import com.moon.hakjumbank.domain.Member;
import com.moon.hakjumbank.domain.Post;
import com.moon.hakjumbank.repository.PostRepository;
import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;

    @Test
    public void 글작성하기() throws Exception {
        //given
        Member member = createMember();

        String title = "제목";
        String content = "내용";

        //when
        Long postId = postService.publishPost(member.getMId(), title, content);

        //then
        Post getPost = postRepository.findOne(postId);

        Assert.assertEquals("제목이 일치해야 한다.", title, getPost.getTitle());
        Assert.assertEquals("내용이 일치해야 한다.", content, getPost.getContent());

    }

    private Member createMember() {
        Member member = new Member();
        member.setMemberName("회원1");
        member.setPassword("1234");
        em.persist(member);
        return member;
    }
}