package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {
}
