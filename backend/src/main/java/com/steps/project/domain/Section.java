package com.steps.project.domain;

import com.steps.common.domain.BaseTimeEntity;
import com.steps.project.dto.request.SectionUpdateRequest;
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

    public void changeOrder(Integer newOrder) {
        this.order = Objects.isNull(newOrder) ? 0 : newOrder;
    }

    public void simpleUpdate(SectionUpdateRequest request) {
        changeName(request.name());
        changeDescription(request.description());
    }

    private void changeName(String newName) {
        this.name = newName;
    }

    private void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void changeProject(Project newProject) {
        if (Objects.nonNull(newProject)) this.project = newProject;
    }
}
