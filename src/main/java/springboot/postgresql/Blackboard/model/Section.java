package springboot.postgresql.Blackboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int capacity;

    @ManyToOne
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "section", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

    public Section() {
    }

    public Section(String title, int capacity, Course course, List<Enrollment> enrollments) {
        this.title = title;
        this.capacity = capacity;
        this.course = course;
        this.enrollments = enrollments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return capacity == section.capacity &&
                Objects.equals(title, section.title) &&
                Objects.equals(course, section.course) &&
                Objects.equals(enrollments, section.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, capacity, course, enrollments);
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", capacity=" + capacity +
                ", course=" + course +
                ", enrollments=" + enrollments +
                '}';
    }
}
