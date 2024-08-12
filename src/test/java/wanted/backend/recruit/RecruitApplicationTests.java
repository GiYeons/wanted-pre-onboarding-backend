package wanted.backend.recruit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanted.backend.recruit.entity.Company;
import wanted.backend.recruit.repository.CompanyRepository;
import wanted.backend.recruit.repository.JobPostRepository;

@SpringBootTest
class RecruitApplicationTests {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private JobPostRepository jobPostRepository;

	@Test
	void testJpa() {

	}

}
