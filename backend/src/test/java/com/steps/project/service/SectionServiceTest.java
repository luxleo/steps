package com.steps.project.service;

import com.steps.member.domain.Member;
import com.steps.project.domain.Project;
import com.steps.project.domain.repository.ProjectRepository;
import com.steps.project.dto.request.SectionCreateRequest;
import com.steps.project.dto.response.SectionCreateResponse;
import com.steps.util.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Section service 테스트")
class SectionServiceTest extends ServiceTest {

    @Autowired
    SectionService sectionService;
    @Autowired
    ProjectRepository projectRepository;

    @DisplayName("프로젝트의 섹션 생성 가능하다.")
    @Test
    void createSection() {
        String sectionName = "test section";
        String sectionDescription = "test description";

        Member member = defaultMember;
        Project project = projectRepository.findAllByOwnerId(member.getId()).stream().findFirst().orElseThrow();
        SectionCreateResponse section = sectionService.createSection(new SectionCreateRequest(project.getId(), sectionName, sectionDescription, 1));

        Assertions.assertThat(section).isNotNull();

    }
}
