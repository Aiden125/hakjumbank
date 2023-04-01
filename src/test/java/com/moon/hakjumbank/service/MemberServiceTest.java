package com.moon.hakjumbank.service;

import com.moon.hakjumbank.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.moon.hakjumbank.repository.MemberRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest // 테스트 환경에서 autowired 하게 해줌
@Transactional // 테스트 끝나면 롤백 용도
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("moon");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class) // 예외를 기대
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("moon1");

        Member member2 = new Member();
        member2.setName("moon1");

        //when
        memberService.join(member1);
        memberService.join(member2); // 여기서 예외가 터져야한다.

        //then
        fail("예외가 발생해야 한다."); // 여기까지 내려오면 실패
    }
}