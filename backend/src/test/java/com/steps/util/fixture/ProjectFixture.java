package com.steps.util.fixture;

import com.steps.member.domain.Member;
import com.steps.project.domain.Project;

public class ProjectFixture {
    public static final Project A_PROJECT_1 = Project.builder()
            .name("A Project 1")
            .description("A Project 1 Description")
            .build();
    public static final Project A_PROJECT_2 = Project.builder()
            .name("A Project 2")
            .description("A Project 2 Description")
            .build();

    public static Project allocateOwner(Project targetProject, Member owner) {
        targetProject.changeOwner(owner);
        return targetProject;
    }
}
