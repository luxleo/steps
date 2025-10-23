package com.steps.util.fixture;

import com.steps.project.domain.Project;
import com.steps.project.domain.Section;

import java.util.List;

public class SectionFixture {
    // SECTION_SET_A_3 means project A has 3 sections
    public static final List<Section> SECTION_SET_A_3 = List.of(new Section[]{
            new Section(null, null, "a-1", "a-1", 0),
            new Section(null, null, "a-2", "a-2", 1),
            new Section(null, null, "a-3", "a-3", 2)
    });

    public static final List<Section> SECTION_SET_B_3 = List.of(new Section[]{
            new Section(null, null, "b-1", "a-1", 0),
            new Section(null, null, "b-2", "a-2", 1),
            new Section(null, null, "b-3", "a-3", 2)
    });

    public static void allocateProjectToSetA(Project project) {
        if(project.getName().equals(ProjectFixture.A_PROJECT.getName()))
            SECTION_SET_A_3
                .forEach(section -> section.changeProject(project));
    }

    public static void allocateProjectToSetB(Project project) {
        if(project.getName().equals(ProjectFixture.B_PROJECT.getName()))
            SECTION_SET_B_3
                .forEach(section -> section.changeProject(project));
    }
}
