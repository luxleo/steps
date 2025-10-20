package com.steps.util.fixture;

import com.steps.member.domain.Member;
import com.steps.member.domain.MemberRole;

public class MemberFixture {
    public static final Member NORMAL = Member.builder()
            .name("test")
            .email("test@gmail.com")
            .password("$2a$10$LGfhU7N/xZRhr5BifmXzq.8z7kjQK/2sRqY2dBjfT/QorlpNeRsW2")
            .role(MemberRole.ROLE_USER)
            .build();
}
