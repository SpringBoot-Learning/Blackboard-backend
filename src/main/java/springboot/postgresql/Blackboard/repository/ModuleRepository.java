package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.postgresql.Blackboard.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
