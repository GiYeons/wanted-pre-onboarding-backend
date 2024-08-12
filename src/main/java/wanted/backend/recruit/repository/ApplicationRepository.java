package wanted.backend.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.backend.recruit.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findByUserIdAndJobPostId(Long user_id, Long job_post_id);
}
