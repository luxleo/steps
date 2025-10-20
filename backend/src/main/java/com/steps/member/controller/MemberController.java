package com.steps.member.controller;

import com.steps.member.dto.request.MemberCreateRequest;
import com.steps.member.dto.response.MemberCreateResponse;
import com.steps.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody MemberCreateRequest request) {
        MemberCreateResponse createResponse = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members" + "/" + createResponse.email())).build();
    }
}
