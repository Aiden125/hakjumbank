package com.moon.hakjumbank.repository;

import com.moon.hakjumbank.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *  DB와 통신하는 역할 xml과 비슷
 * */
@Repository
@RequiredArgsConstructor // final 있는 애 생성자를 만들어줌
public class MemberRepository {

    private final EntityManager em;

    // 회원가입
    public void save(Member member) {
        em.persist(member);
    }

    // 멤버 찾기
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 전체조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름으로 멤버 찾기
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name=:name", Member.class) // m:name 은 파라미터를 뜻함
                .setParameter("name", name)
                .getResultList();
    }

}
