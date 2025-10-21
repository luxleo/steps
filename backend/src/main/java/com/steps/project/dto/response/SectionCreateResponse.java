package com.steps.project.dto.response;

import com.steps.project.domain.Section;

public record SectionCreateResponse(
        Long projectId,
        Long id,
        String name,
        Integer order
) {
    public static SectionCreateResponse from(Section newSection) {
        return new SectionCreateResponse(
            newSection.getProject().getId(),
            newSection.getId(),
            newSection.getName(),
            newSection.getOrder()
        );
    }
}
