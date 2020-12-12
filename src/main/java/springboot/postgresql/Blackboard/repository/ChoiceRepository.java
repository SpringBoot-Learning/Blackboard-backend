package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.postgresql.Blackboard.model.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
}
