package com.steps.project.domain.repository;

import com.steps.project.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    @Query("SELECT s FROM Section s WHERE s.project.id = :projectId ORDER BY s.order")
    List<Section> findAllByProjectId(Long projectId);
}
