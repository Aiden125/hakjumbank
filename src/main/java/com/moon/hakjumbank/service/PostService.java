package com.moon.hakjumbank.service;

import com.moon.hakjumbank.domain.Member;
import com.moon.hakjumbank.repository.MemberRepository;
import com.moon.hakjumbank.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 실제 비지니스 동작이 들어가는 service */
@Service
@Transactional(readOnly = true) // 모든 비지니스 로직은 트랜잭셔널이 들어가야하고 readOnly로 지정
@RequiredArgsConstructor // final 있는 필드만 가지고 생성자를 만들어줌
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(Long memberId, int count) {
        Member member = memberRepository.findByName()
    }

}
