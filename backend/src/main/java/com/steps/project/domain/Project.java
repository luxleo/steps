package com.steps.project.domain;

import com.steps.common.domain.BaseTimeEntity;
import com.steps.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "project")
public class Project extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;
    private String name;
    private String description;

    @Builder
    public Project(Long id, Member owner, String name, String description) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.description = description;
    }
}
