package com.steps.project.domain;

import com.steps.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "section")
@Entity
public class Section extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private String name;

    private String description;

    @Column(name = "order_index")
    private Integer order;

    @Builder
    public Section(Long id, Project project, String name, String description, Integer order) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.description = description;
        this.order = Objects.isNull(order) ? 0 : order;
    }
}
