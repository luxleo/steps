package com.steps.project.dto.response;

import com.steps.project.domain.Project;

public record ProjectListResponse(
    Long projectId,
    String name
) {
    public static ProjectListResponse from(Project project) {
        return new ProjectListResponse(project.getId(), project.getName());
    }
}
