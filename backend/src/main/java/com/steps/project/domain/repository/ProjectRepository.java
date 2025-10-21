package com.steps.project.domain.repository;

import com.steps.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    //TODO: 무한스크롤 기반 Page로 반환하기
    List<Project> findAllByOwnerId(Long ownerId);
}
