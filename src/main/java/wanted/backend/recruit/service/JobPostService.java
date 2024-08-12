package wanted.backend.recruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.recruit.dto.JobPostDetailResponse;
import wanted.backend.recruit.dto.JobPostRequest;
import wanted.backend.recruit.dto.JobPostResponse;
import wanted.backend.recruit.dto.SuccessResponse;
import wanted.backend.recruit.entity.Company;
import wanted.backend.recruit.entity.JobPost;
import wanted.backend.recruit.repository.CompanyRepository;
import wanted.backend.recruit.repository.JobPostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private final CompanyRepository companyRepository;

    // 채용공고 등록
    @Transactional
    public JobPostResponse createJobPost(JobPostRequest request) {
        Company company = companyRepository.findById(request.getCompany_id())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회사가 없습니다. " + request.getCompany_id()));

        JobPost jobPost = new JobPost(request, company);
        jobPostRepository.save(jobPost);
        return new JobPostResponse(jobPost);
    }

    // 채용공고 수정
    @Transactional
    public JobPostResponse updateJobPost(Long id, JobPostRequest request) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        jobPost.update(request);
        return new JobPostResponse(jobPost);
    }

    // 채용공고 삭제
    @Transactional
    public SuccessResponse deleteJobPost(Long id, JobPostRequest request) throws Exception{
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        jobPostRepository.deleteById(id);
        return new SuccessResponse(true);
    }

    // 채용공고 목록 GET (검색 기능 포함)
    @Transactional
    public List<JobPostResponse> getJobPosts(String search) {
        // 검색어가 없는 경우 전부 반환
        if (search == null || search.isBlank()) {
            return jobPostRepository.findAll()
                    .stream()
                    .map(JobPostResponse::new)
                    .toList();
        }
        // 검색어가 존재하는 경우 검색 결과 반환
        else {
            return jobPostRepository.searchJobPost(search)
                    .stream()
                    .map(JobPostResponse::new)
                    .toList();
        }
    }

    // 채용 상세페이지 GET
    @Transactional
    public JobPostDetailResponse getJobPost(Long id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 해당 회사의 현재 채용공고를 제외한 다른 채용공고의 id 목록 포함
        return new JobPostDetailResponse(
                jobPost,
                jobPostRepository.findByCompanyExceptForCurrentJobPost(
                        jobPost.getCompany().getId(),
                        jobPost.getId()
                ));
    }
}
