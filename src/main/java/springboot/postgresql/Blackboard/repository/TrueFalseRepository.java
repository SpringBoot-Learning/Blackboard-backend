package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.TrueFalse;

public interface TrueFalseRepository extends JpaRepository<TrueFalse, Integer> {
}
