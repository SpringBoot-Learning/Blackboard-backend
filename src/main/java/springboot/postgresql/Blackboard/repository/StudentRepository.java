package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
