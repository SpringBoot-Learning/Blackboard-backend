package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.postgresql.Blackboard.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>  {


    @Query(value = "Select * from course mycourse where mycourse.label=:label", nativeQuery=true)
    public Course findCourseByLabel(@Param("label") String label);

}
