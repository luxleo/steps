package com.steps.project.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ProjectCreateRequest(
        @NotEmpty String name,
        @NotEmpty String description
) {
}
