package com.steps.member.service;

import com.steps.common.domain.BaseTimeEntity;
import com.steps.member.domain.Member;
import com.steps.member.domain.repository.MemberRepository;
import com.steps.member.dto.request.MemberCreateRequest;
import com.steps.member.dto.response.MemberCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService extends BaseTimeEntity {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberCreateResponse createMember(MemberCreateRequest request) {
        Member newMember = Member.builder()
                .email(request.email())
                .name(request.name())
                .password(passwordEncoder.encode(request.password()))
                .build();
        Member createdMember = memberRepository.save(newMember);
        return MemberCreateResponse.from(createdMember);
    }

    public Member findMemberByEmail(String email) {
        //TODO: redis에서 email로 등록된 유저 id 먼저 조회후 있으면 id로 조회, 없으면 인덱스 이용
        return memberRepository.findByEmail(email)
                .orElseThrow();
    }
}
