package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
