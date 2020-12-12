package springboot.postgresql.Blackboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.postgresql.Blackboard.model.Course;
import springboot.postgresql.Blackboard.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value = "select * from person myperson where myperson.username=:username and myperson.password=:password", nativeQuery=true)
    public Person autenticatePerson(@Param("username") String username, @Param("password") String password);
}
