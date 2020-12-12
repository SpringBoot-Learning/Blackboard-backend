package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.postgresql.Blackboard.model.TrueFalse;

@Repository
public interface TrueFalseRepository extends JpaRepository<TrueFalse, Integer> {
}
