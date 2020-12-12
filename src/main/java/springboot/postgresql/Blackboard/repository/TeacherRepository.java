package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.postgresql.Blackboard.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query(value = "Select teacher from Teacher teacher where teacher.username=:username", nativeQuery=true)
    public Teacher findTeacherByUsername(@Param("username") String username);
}
