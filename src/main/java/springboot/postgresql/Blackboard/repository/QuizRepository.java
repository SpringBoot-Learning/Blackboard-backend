package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.postgresql.Blackboard.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
