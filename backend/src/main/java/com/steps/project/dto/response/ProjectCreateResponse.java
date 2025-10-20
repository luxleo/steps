package com.steps.project.dto.response;

import com.steps.project.domain.Project;

public record ProjectCreateResponse(
        Long projectId,
        String name,
        String description
) {
    public static ProjectCreateResponse from(Project project) {
        return new ProjectCreateResponse(project.getId(), project.getName(), project.getDescription());
    }
}
