package springboot.postgresql.Blackboard.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Teacher extends Person {

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(String name, int age, String email, String address, long phoneNo, String password, String username, List<Course> courses) {
        super(name, age, email, address, phoneNo, password, username);
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return courses.equals(teacher.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courses);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "courses=" + courses +
                '}';
    }
}
