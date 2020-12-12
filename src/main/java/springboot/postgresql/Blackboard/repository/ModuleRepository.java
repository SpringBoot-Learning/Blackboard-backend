package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.postgresql.Blackboard.model.Course;
import springboot.postgresql.Blackboard.model.Module;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

    @Query(value = "Select * from module mymodule where mymodule.label=:label", nativeQuery=true)
    public List<Module> findModuleByLabel(@Param("label") String label);
}
