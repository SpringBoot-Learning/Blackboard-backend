package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.postgresql.Blackboard.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
