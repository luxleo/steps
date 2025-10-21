package com.steps.util;

import com.steps.member.domain.Member;
import com.steps.member.domain.repository.MemberRepository;
import com.steps.project.domain.repository.ProjectRepository;
import com.steps.util.fixture.MemberFixture;
import com.steps.util.fixture.ProjectFixture;
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
    private DBCleaner dbCleaner;

    @BeforeEach
    void resetDB() {
        dbCleaner.clean();
        defaultMember = memberRepository.save(MemberFixture.NORMAL);
        projectRepository.save(ProjectFixture.allocateOwner(ProjectFixture.A_PROJECT_1, defaultMember));
        projectRepository.save(ProjectFixture.allocateOwner(ProjectFixture.A_PROJECT_2, defaultMember));
    }
}
