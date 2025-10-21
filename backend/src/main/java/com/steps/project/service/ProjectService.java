package com.steps.project.service;

import com.steps.member.domain.Member;
import com.steps.member.service.MemberService;
import com.steps.project.domain.Project;
import com.steps.project.domain.repository.ProjectRepository;
import com.steps.project.dto.request.ProjectCreateRequest;
import com.steps.project.dto.response.ProjectCreateResponse;
import com.steps.project.dto.response.ProjectListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProjectService {

    private final MemberService memberService;
    private final ProjectRepository projectRepository;

    /**
     *
     * @param ownerEmail Spring Security의 Authentication 으로 부터 획득한 Member이다.
     * @param request
     * @return
     */
    @Transactional
    public ProjectCreateResponse createProject(String ownerEmail, ProjectCreateRequest request) {
        Member owner = memberService.findMemberByEmail(ownerEmail);
        Project newProject = projectRepository.save(
                Project.builder()
                .owner(owner)
                .name(request.name())
                .description(request.description())
                .build()
        );
        return ProjectCreateResponse.from(newProject);
    }


    public List<ProjectListResponse> findProjectsForMember(Long memberId) {
        //TODO: 이후 본인 소유 프로젝트 + 초대된 프로젝트 합해서 반환하기
        return projectRepository.findAllByOwnerId(memberId).stream()
                .map(ProjectListResponse::from)
                .toList();
    }
}
