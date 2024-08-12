package wanted.backend.recruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.recruit.dto.JobPostRequest;
import wanted.backend.recruit.dto.JobPostResponse;
import wanted.backend.recruit.dto.SuccessResponse;
import wanted.backend.recruit.entity.Company;
import wanted.backend.recruit.entity.JobPost;
import wanted.backend.recruit.repository.CompanyRepository;
import wanted.backend.recruit.repository.JobPostRepository;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public JobPostResponse createJobPost(JobPostRequest request) {
        Company company = companyRepository.findById(request.getCompany_id())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회사가 없습니다. " + request.getCompany_id()));

        JobPost jobPost = new JobPost(request, company);
        jobPostRepository.save(jobPost);
        return new JobPostResponse(jobPost);
    }

    @Transactional
    public JobPostResponse updateJobPost(Long id, JobPostRequest request) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        jobPost.update(request);
        return new JobPostResponse(jobPost);
    }

    @Transactional
    public SuccessResponse deleteJobPost(Long id, JobPostRequest request) throws Exception{
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        jobPostRepository.deleteById(id);
        return new SuccessResponse(true);
    }
}
