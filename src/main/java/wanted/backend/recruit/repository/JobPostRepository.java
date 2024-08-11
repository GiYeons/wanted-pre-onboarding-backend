package wanted.backend.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.backend.recruit.entity.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}
