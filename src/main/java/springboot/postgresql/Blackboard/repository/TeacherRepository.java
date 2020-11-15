package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
