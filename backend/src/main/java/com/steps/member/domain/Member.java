package com.steps.member.domain;

import com.steps.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
@Entity
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "password_hash")
    private String password;
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String name, String email, String password, MemberRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImageUrl = "";
        this.role = Objects.isNull(role) ? MemberRole.ROLE_USER : role;
    }
}
