package wanted.backend.recruit.dto.jobPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.backend.recruit.entity.Company;
import wanted.backend.recruit.entity.JobPost;

@Getter
@NoArgsConstructor
public class JobPostResponse {
    private Long id;
    private Long company_id;
    private String company_name;
    private String position;
    private Long reward;
    private String content;
    private String skill;
    private String nation;
    private String region;

    public JobPostResponse(JobPost jobPost) {
        this.id = jobPost.getId();
        this.company_id = jobPost.getCompany().getId();
        this.company_name = jobPost.getCompany().getName();
        this.position = jobPost.getPosition();
        this.reward = jobPost.getReward();
        this.content = jobPost.getContent();
        this.skill = jobPost.getSkill();
        this.nation = jobPost.getNation();
        this.region = jobPost.getRegion();
    }
}
