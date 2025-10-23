package com.steps.util;

import com.steps.member.domain.Member;
import com.steps.member.domain.repository.MemberRepository;
import com.steps.project.domain.Project;
import com.steps.project.domain.repository.ProjectRepository;
import com.steps.project.domain.repository.SectionRepository;
import com.steps.util.fixture.MemberFixture;
import com.steps.util.fixture.ProjectFixture;
import com.steps.util.fixture.SectionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
public class ServiceTest {
    protected Member defaultMember;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private DBCleaner dbCleaner;

    @BeforeEach
    void resetDB() {
        dbCleaner.clean();
        defaultMember = memberRepository.save(MemberFixture.NORMAL);
        Project projectA = projectRepository.save(ProjectFixture.allocateOwner(ProjectFixture.A_PROJECT, defaultMember));
        Project projectB = projectRepository.save(ProjectFixture.allocateOwner(ProjectFixture.B_PROJECT, defaultMember));

        SectionFixture.allocateProjectToSetA(projectA);
        SectionFixture.allocateProjectToSetB(projectB);

        sectionRepository.saveAll(SectionFixture.SECTION_SET_A_3);
        sectionRepository.saveAll(SectionFixture.SECTION_SET_B_3);
    }
}
