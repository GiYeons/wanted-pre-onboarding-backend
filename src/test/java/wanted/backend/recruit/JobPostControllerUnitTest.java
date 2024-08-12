package wanted.backend.recruit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wanted.backend.recruit.controller.JobPostController;
import wanted.backend.recruit.dto.jobPost.JobPostRequest;
import wanted.backend.recruit.service.JobPostService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TDD 테스트 주도 개발
@ExtendWith(MockitoExtension.class)
public class JobPostControllerUnitTest {
    @InjectMocks
    JobPostController jobPostController;

    @Mock
    JobPostService jobPostService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobPostController).build();
    }

    @Test
    @DisplayName("채용공고 등록")
    public void createJobPostTest() throws Exception {
        // given
        JobPostRequest request = JobPostRequest.builder()
                .company_id(1L)
                .position("백엔드 주니어 개발자")
                .reward(400000L)
                .content("백엔드 개발자 구인합니다.")
                .skill("Spring boot")
                .build();

        // when, then
        mockMvc.perform(
                        post("/jobPost/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(jobPostService).createJobPost(request);
    }
}
