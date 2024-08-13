package wanted.backend.recruit.dto.jobPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.backend.recruit.entity.Company;
import wanted.backend.recruit.entity.JobPost;

import java.util.List;

@Getter
@NoArgsConstructor
public class JobPostDetailResponse {
    private Long id;
    private Company company;
    private String position;
    private Long reward;
    private String content;
    private String skill;
    private String nation;
    private String region;
    private List<Integer> other_job_posts;

    public JobPostDetailResponse(JobPost jobPost, List<Integer> otherJobPosts) {
        this.id = jobPost.getId();
        this.company = jobPost.getCompany();
        this.position = jobPost.getPosition();
        this.reward = jobPost.getReward();
        this.content = jobPost.getContent();
        this.skill = jobPost.getSkill();
        this.nation = jobPost.getNation();
        this.region = jobPost.getRegion();
        this.other_job_posts = otherJobPosts;
    }
}
