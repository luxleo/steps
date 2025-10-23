package com.steps.util.fixture;

import com.steps.member.domain.Member;
import com.steps.project.domain.Project;

public class ProjectFixture {
    public static final Project A_PROJECT = Project.builder()
            .name("A Project")
            .description("A Project Description")
            .build();
    public static final Project B_PROJECT = Project.builder()
            .name("B Project")
            .description("B Project Description")
            .build();

    public static Project allocateOwner(Project targetProject, Member owner) {
        targetProject.changeOwner(owner);
        return targetProject;
    }
}
