package com.moon.hakjumbank.service;

import com.moon.hakjumbank.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.hakjumbank.repository.MemberRepository;

import java.util.List;

/** 실제 비지니스 동작이 들어가는 service */
@Service
@Transactional(readOnly = true) // 모든 비지니스 로직은 트랜잭셔널이 들어가야하고 readOnly로 지정
@RequiredArgsConstructor // final 있는 필드만 가지고 생성자를 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

//    public MemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     * */
    @Transactional // 얘는 조회가 아니기 때문에 readOnly 따로 안적음(readOnly = false)가 기본
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getMId();
    }

    // 중복회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getMemberName()); // 완전 동시 방지를 위해 db에 유니크 키를 적용해줘야함
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
