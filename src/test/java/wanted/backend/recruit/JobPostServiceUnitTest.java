package wanted.backend.recruit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wanted.backend.recruit.dto.jobPost.JobPostRequest;
import wanted.backend.recruit.entity.Company;
import wanted.backend.recruit.entity.JobPost;
import wanted.backend.recruit.repository.CompanyRepository;
import wanted.backend.recruit.repository.JobPostRepository;
import wanted.backend.recruit.service.JobPostService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class JobPostServiceUnitTest {
    @InjectMocks
    JobPostService jobPostService;

    @Mock
    JobPostRepository jobPostRepository;

    @Mock
    CompanyRepository companyRepository;

    @Test
    @DisplayName("채용공고 등록 서비스")
    void createJobPostServiceTest() {
        // given
        Company company = Company.builder()
                .name("원티드")
                .build();

        JobPost jobPost = JobPost.builder()
                .company(company)
                .position("백엔드 주니어 개발자")
                .reward(400000L)
                .content("백엔드 개발자 구인합니다.")
                .skill("Spring boot")
                .build();

        JobPostRequest request = JobPostRequest.builder()
                .company_id(company.getId())
                .position("백엔드 주니어 개발자")
                .reward(400000L)
                .content("백엔드 개발자 구인합니다.")
                .skill("Spring boot")
                .build();

        given(companyRepository.save(company)).willReturn(company);
        given(jobPostRepository.save(jobPost)).willReturn(jobPost);

        // when
        jobPostService.createJobPost(request);

        // then
        verify(jobPostRepository).save(any());
    }
}
