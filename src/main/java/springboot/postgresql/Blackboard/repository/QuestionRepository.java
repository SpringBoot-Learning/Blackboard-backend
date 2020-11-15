package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
