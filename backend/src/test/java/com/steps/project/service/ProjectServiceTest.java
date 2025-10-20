package com.steps.project.service;

import com.steps.project.dto.request.ProjectCreateRequest;
import com.steps.project.dto.response.ProjectCreateResponse;
import com.steps.util.ServiceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("프로젝트 테스트")
class ProjectServiceTest extends ServiceTest {

    @Autowired
    ProjectService projectService;

    @DisplayName("프로젝트 생성시 유효한 멤버 ID와 함께 생성된다.")
    @Test
    void createProject() {
        String projectName = "test";
        String projectDescription = "test description";
        ProjectCreateRequest request = new ProjectCreateRequest(projectName, projectDescription);

        ProjectCreateResponse newProject = projectService.createProject(defaultMember.getEmail(),request);

        assertThat(newProject.name()).isEqualTo(projectName);
        assertThat(newProject.description()).isEqualTo(projectDescription);
    }
}
