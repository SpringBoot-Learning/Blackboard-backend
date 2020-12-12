package springboot.postgresql.Blackboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Enrollment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private int grade;

    @ManyToOne
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JsonIgnore
    private Section section;

    public Enrollment() {
    }

    public Enrollment(int grade, Student student, Section section) {
        this.grade = grade;
        this.student = student;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return grade == that.grade &&
                student.equals(that.student) &&
                section.equals(that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, student, section);
    }

}
