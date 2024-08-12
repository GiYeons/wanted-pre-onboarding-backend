package wanted.backend.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wanted.backend.recruit.entity.JobPost;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    @Query("SELECT jp.id FROM JobPost jp WHERE jp.company.id = :companyId AND " +
            "jp.id <> :currentJobPostId ORDER BY jp.id DESC")
    List<Integer> findByCompanyExceptForCurrentJobPost(
            @Param("companyId") Long companyId,
            @Param("currentJobPostId") Long currentJobPostId);
}

