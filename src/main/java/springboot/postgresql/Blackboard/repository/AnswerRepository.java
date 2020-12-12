package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.postgresql.Blackboard.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
