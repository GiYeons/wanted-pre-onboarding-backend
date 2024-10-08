package wanted.backend.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.backend.recruit.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
