package com.steps.project.service;

import com.steps.member.domain.Member;
import com.steps.project.domain.Project;
import com.steps.project.domain.Section;
import com.steps.project.domain.repository.ProjectRepository;
import com.steps.project.domain.repository.SectionRepository;
import com.steps.project.dto.request.SectionCreateRequest;
import com.steps.project.dto.request.SectionUpdateRequest;
import com.steps.project.dto.response.SectionCreateResponse;
import com.steps.project.dto.response.SectionListResponse;
import com.steps.util.ServiceTest;
import com.steps.util.fixture.SectionFixture;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@DisplayName("Section service 테스트")
class SectionServiceTest extends ServiceTest {

    @Autowired
    SectionService sectionService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    private SectionRepository sectionRepository;

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

    @DisplayName("프로젝트 하위의 섹션들을 조회할 수 있다.")
    @Test
    void findSectionByProject() {
        Member member = defaultMember;
        Project project = projectRepository.findAllByOwnerId(member.getId()).stream().findFirst().orElseThrow();
        log.info("project id: {}", project.getId());
        List<SectionListResponse> sections = sectionService.findSectionByProject(project.getId());
        Assertions.assertThat(sections).hasSize(SectionFixture.SECTION_SET_A_3.size());
    }

    @DisplayName("섹션의 이름, 설명 등의 간단한 필드를 수정한다.")
    @Test
    void updateSimple() {
        Long projectId = 1L;
        SectionListResponse sectionA = sectionService.findSectionByProject(projectId).stream().findFirst().orElseThrow();
        String newName = "new name";
        String newDescription = "new description";

        sectionService.updateSection(sectionA.SectionId(), new SectionUpdateRequest(newName, newDescription));

        Section updatedSection = sectionRepository.findById(sectionA.SectionId()).orElseThrow();
        Assertions.assertThat(updatedSection.getName()).isEqualTo(newName);
        Assertions.assertThat(updatedSection.getDescription()).isEqualTo(newDescription);
    }
}
