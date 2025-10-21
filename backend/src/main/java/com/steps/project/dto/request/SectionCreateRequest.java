package com.steps.project.dto.request;

import com.steps.project.domain.Project;
import com.steps.project.domain.Section;

public record SectionCreateRequest(
    Long projectId,
    String name,
    String description,
    Integer order
) {
    public Section toSection(Project project) {
        return Section.builder()
            .project(project)
            .name(name)
            .description(description)
            .order(order)
            .build();
    }
}
