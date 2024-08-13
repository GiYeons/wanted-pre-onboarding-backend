package wanted.backend.recruit.controller;

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
import wanted.backend.recruit.dto.application.ApplicationRequest;
import wanted.backend.recruit.dto.jobPost.JobPostRequest;
import wanted.backend.recruit.service.ApplicationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ApplicationControllerUnitTest {
    @InjectMocks
    ApplicationController applicationController;

    @Mock
    ApplicationService applicationService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(applicationController).build();
    }

    @Test
    @DisplayName("채용공고 지원")
    public void createApplicationTest() throws Exception {
        // given
        ApplicationRequest request = ApplicationRequest.builder()
                .user_id(1L)
                .job_post_id(1L)
                .build();

        // when, then
        mockMvc.perform(
                        post("/application/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
