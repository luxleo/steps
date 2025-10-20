package com.steps.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record MemberCreateRequest(
        @Email String email,
        @NotEmpty String name,
        @NotEmpty String password
)  {
}
