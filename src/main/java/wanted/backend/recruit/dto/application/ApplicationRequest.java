package wanted.backend.recruit.dto.application;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationRequest {
    private Long job_post_id;
    private Long user_id;

    // 테스트용 빌더
    @Builder
    ApplicationRequest(Long job_post_id, Long user_id) {
        this.job_post_id = job_post_id;
        this.user_id = user_id;
    }
}
