package wanted.backend.recruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.backend.recruit.dto.application.ApplicationRequest;
import wanted.backend.recruit.dto.application.ApplicationResponse;
import wanted.backend.recruit.entity.Application;
import wanted.backend.recruit.entity.JobPost;
import wanted.backend.recruit.entity.User;
import wanted.backend.recruit.repository.ApplicationRepository;
import wanted.backend.recruit.repository.JobPostRepository;
import wanted.backend.recruit.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    @Transactional
    public ApplicationResponse createApplication(ApplicationRequest request) {
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        JobPost jobPost = jobPostRepository.findById(request.getJob_post_id())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 채용공고가 없습니다."));

        // 이미 해당 사용자가 해당 채용공고에 지원했는지 확인
        Optional.ofNullable(applicationRepository.findByUserIdAndJobPostId(user.getId(), jobPost.getId()))
                .ifPresent(application -> {
                    throw new IllegalStateException("이미 해당 채용공고에 지원했습니다.");
                });

        // 지원 내역이 없으면 새로운 Application 생성 및 저장
        Application application = Application.builder()
                .user(user)
                .jobPost(jobPost)
                .build();
        applicationRepository.save(application);

        // ApplicationResponse로 반환
        return new ApplicationResponse(application);
    }
}
