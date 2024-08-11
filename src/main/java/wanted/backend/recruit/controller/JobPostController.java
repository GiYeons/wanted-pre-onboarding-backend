package wanted.backend.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wanted.backend.recruit.dto.JobPostRequest;
import wanted.backend.recruit.dto.JobPostResponse;
import wanted.backend.recruit.service.JobPostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobPost")
public class JobPostController {
    private final JobPostService jobPostService;

    @PostMapping("/create")
    public JobPostResponse createJobPost(@RequestBody JobPostRequest request) {
        return jobPostService.createJobPost(request);
    }

    @PutMapping("/update/{id}")
    public  JobPostResponse updateJobPost(@PathVariable Long id, @RequestBody JobPostRequest request) throws Exception {
        return jobPostService.updateJobPost(id, request);
    }
}
