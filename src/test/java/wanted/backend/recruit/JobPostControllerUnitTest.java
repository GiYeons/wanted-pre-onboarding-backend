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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("채용공고 수정")
    public void updateJobPostTest() throws Exception {
        // given
        Long id = 1L;
        JobPostRequest request = JobPostRequest.builder()
                .company_id(1L)
                .position("백엔드 주니어 개발자")
                .reward(400000L)
                .content("백엔드 개발자 구인합니다.")
                .skill("Spring boot")
                .build();

        // when, then
        mockMvc.perform(
                        put("/jobPost/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());

        assertThat(request.getContent()).isEqualTo("백엔드 개발자 구인합니다.");

    }

    @Test
    @DisplayName("채용공고 삭제")
    public void deleteJobPostTest() throws Exception {
        // given
        Long id = 1L;

        // when, then
        mockMvc.perform(
                delete("/jobPost/{id}", id))
                .andExpect(status().isOk());

//        verify(jobPostService).deleteJobPost(id);
    }

    @Test
    @DisplayName("채용공고 목록 조회")
    public void getJobPostsTest() throws Exception {
        // given
        String search1 = "";
        String search2 = "프론트";     // 검색어

        // when, then
        mockMvc.perform(
                        get(String.format("/jobPost/all?search=%s", search2)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(jobPostService).getJobPosts(search2);
    }

    @Test
    @DisplayName("채용 상세 페이지 조회")
    public void getJobPostTest() throws Exception {
        // given
        Long id = 1L;

        // when, then
        mockMvc.perform(
                get("/jobPost/{id}", id))
                .andExpect(status().isOk());
        verify(jobPostService).getJobPost(id);
    }
}
