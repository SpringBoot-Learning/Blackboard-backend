package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}
