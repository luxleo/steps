package com.steps.member.service;

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
public class MemberService {

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
        return new MemberCreateResponse(createdMember.getId(), createdMember.getEmail());
    }
}
