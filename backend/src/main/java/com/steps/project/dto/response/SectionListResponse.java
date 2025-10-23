package com.steps.project.dto.response;

import com.steps.project.domain.Section;

public record SectionListResponse(
        Long SectionId,
        String name,
        Integer order
) {
    public static SectionListResponse from(Section section) {
        return new SectionListResponse(section.getId(), section.getName(), section.getOrder());
    }
}
