package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.MultipleChoice;

public interface MultipleChoiceRepository extends JpaRepository<MultipleChoice, Integer> {
}
