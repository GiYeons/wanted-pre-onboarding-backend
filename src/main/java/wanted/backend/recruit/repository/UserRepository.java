package wanted.backend.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.backend.recruit.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
