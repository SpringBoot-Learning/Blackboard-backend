package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
