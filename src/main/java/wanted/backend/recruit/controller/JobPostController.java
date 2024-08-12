package wanted.backend.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wanted.backend.recruit.dto.jobPost.JobPostDetailResponse;
import wanted.backend.recruit.dto.jobPost.JobPostRequest;
import wanted.backend.recruit.dto.jobPost.JobPostResponse;
import wanted.backend.recruit.dto.SuccessResponse;
import wanted.backend.recruit.service.JobPostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobPost")
public class JobPostController {
    private final JobPostService jobPostService;

    // 채용공고 등록
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    public JobPostResponse createJobPost(@RequestBody JobPostRequest request) {
        return jobPostService.createJobPost(request);
    }

    // 채용공고 수정
    @PutMapping("/{id}")
    public JobPostResponse updateJobPost(@PathVariable Long id, @RequestBody JobPostRequest request) {
        return jobPostService.updateJobPost(id, request);
    }

    // 채용공고 삭제
    @DeleteMapping("/{id}")
    public SuccessResponse deleteJobPost(@PathVariable Long id) throws Exception {
        return jobPostService.deleteJobPost(id);
    }

    // 채용공고 목록 GET (검색 기능 포함)
    @GetMapping("/all")
    public List<JobPostResponse> getJobPosts(@RequestParam(required = false, defaultValue = "") String search) {
        return jobPostService.getJobPosts(search);
    }

    // 채용 상세 페이지 GET
    @GetMapping("/{id}")
    public JobPostDetailResponse getJobPost(@PathVariable Long id) {
        return jobPostService.getJobPost(id);
    }
}
