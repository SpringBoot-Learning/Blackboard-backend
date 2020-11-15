package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
