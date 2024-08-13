package wanted.backend.recruit.dto.jobPost;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JobPostRequest {
    private Long company_id;
    private String position;
    private Long reward;
    private String content;
    private String skill;
    private String nation;
    private String region;

    // 테스트용 빌더
    @Builder
    JobPostRequest(Long company_id, String position, Long reward, String content, String skill) {
        this.company_id = company_id;
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
    }
}
