package wanted.backend.recruit.dto.jobPost;

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
}
