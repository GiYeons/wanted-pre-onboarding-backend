package wanted.backend.recruit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.backend.recruit.dto.jobPost.JobPostRequest;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_post")
public class JobPost {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @Column(name="position", nullable = false)
    private String position;

    @Column(name="reward", nullable = false)
    private Long reward;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="skill")
    private String skill;

    @Column(name="nation")
    private String nation;

    @Column(name="region")
    private String region;

    @Builder
    public JobPost(Company company, String position, Long reward, String content, String skill, String nation, String region) {
        this.company = company;
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
        this.nation = nation;
        this.region = region;
    }

    public void update(JobPostRequest request) {
        this.position = request.getPosition();
        this.reward = request.getReward();
        this.content = request.getContent();
        this.skill = request.getSkill();
        this.nation = request.getNation();
        this.region = request.getRegion();
    }
}
