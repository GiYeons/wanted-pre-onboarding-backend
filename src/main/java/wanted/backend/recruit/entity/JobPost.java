package wanted.backend.recruit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

    public JobPost(JobPostRequest request, Company company) {
        this.company = company;
        this.position = request.getPosition();
        this.reward = request.getReward();
        this.content = request.getContent();
        this.skill = request.getSkill();
        this.nation = request.getNation();
        this.region = request.getRegion();
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
