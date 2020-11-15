package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
}
