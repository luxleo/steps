package com.steps.project.service;

import com.steps.project.domain.Project;
import com.steps.project.domain.Section;
import com.steps.project.domain.repository.ProjectRepository;
import com.steps.project.domain.repository.SectionRepository;
import com.steps.project.dto.request.SectionCreateRequest;
import com.steps.project.dto.request.SectionUpdateRequest;
import com.steps.project.dto.response.SectionCreateResponse;
import com.steps.project.dto.response.SectionListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SectionService {

    private final ProjectRepository projectRepository;
    private final SectionRepository sectionRepository;

    @Transactional
    public SectionCreateResponse createSection(SectionCreateRequest request) {
        Project project = projectRepository.findById(request.projectId()).orElseThrow();
        Section newSection = sectionRepository.save(request.toSection(project));
        return SectionCreateResponse.from(newSection);
    }
    //TODO: Section reorder: Project 에 속한 모든 Section을 reordering 하여 request에 보낸다. 즉 Project 밑의 Section 순서의 전체 업데이트를 진행한다.

    public List<SectionListResponse> findSectionByProject(Long projectId) {
        List<Section> sections = sectionRepository.findAllByProjectId(projectId);
        return sections.stream()
                .map(SectionListResponse::from)
                .toList();
    }

    /**
     * 섹션 이름이나 설명을 바꾸는 간단한 업데이트를 진행할 때 사용한다.
     * @param sectionId
     * @param request {@link SectionUpdateRequest}
     */
    @Transactional
    public void updateSection(Long sectionId, SectionUpdateRequest request) {
        Section findSection = sectionRepository.findById(sectionId).orElseThrow();
        findSection.simpleUpdate(request);
    }
}
