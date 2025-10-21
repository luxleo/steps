package com.steps.project.service;

import com.steps.project.domain.Project;
import com.steps.project.domain.Section;
import com.steps.project.domain.repository.ProjectRepository;
import com.steps.project.domain.repository.SectionRepository;
import com.steps.project.dto.request.SectionCreateRequest;
import com.steps.project.dto.response.SectionCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
