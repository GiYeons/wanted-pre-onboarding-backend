package wanted.backend.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wanted.backend.recruit.dto.JobPostDetailResponse;
import wanted.backend.recruit.dto.JobPostRequest;
import wanted.backend.recruit.dto.JobPostResponse;
import wanted.backend.recruit.dto.SuccessResponse;
import wanted.backend.recruit.service.JobPostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobPost")
public class JobPostController {
    private final JobPostService jobPostService;

    // 채용공고 등록
    @PostMapping("/create")
    public JobPostResponse createJobPost(@RequestBody JobPostRequest request) {
        return jobPostService.createJobPost(request);
    }

    // 채용공고 수정
    @PutMapping("/{id}")
    public JobPostResponse updateJobPost(@PathVariable Long id, @RequestBody JobPostRequest request) throws Exception {
        return jobPostService.updateJobPost(id, request);
    }

    // 채용공고 삭제
    @DeleteMapping("/{id}")
    public SuccessResponse deleteJobPost(@PathVariable Long id, @RequestBody JobPostRequest request) throws Exception {
        return jobPostService.deleteJobPost(id, request);
    }

    // 채용공고 목록 GET
    @GetMapping("/all")
    public List<JobPostResponse> getJobPosts() {
        return jobPostService.getJobPosts();
    }

    // 채용 상세 페이지 GET
    @GetMapping("/{id}")
    public JobPostDetailResponse getJobPost(@PathVariable Long id) {
        return jobPostService.getJobPost(id);
    }
}
