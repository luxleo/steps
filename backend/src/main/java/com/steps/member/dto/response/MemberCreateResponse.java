package com.steps.member.dto.response;

import com.steps.member.domain.Member;

public record MemberCreateResponse(
        Long id,
        String email
) {
    public static MemberCreateResponse from(Member member) {
        return new MemberCreateResponse(member.getId(), member.getEmail());
    }
}
