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
public class Module {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    private Course course;

    private String label;

    private String description;

    @OneToMany(mappedBy = "module", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Quiz> quizzes;

    public Module() {
    }

    public Module(Course course, String description, List<Quiz> quizzes) {
        this.course = course;
        this.description = description;
        this.quizzes = quizzes;
    }

    public Module(Course course, String label, String description, List<Quiz> quizzes) {
        this.course = course;
        this.label = label;
        this.description = description;
        this.quizzes = quizzes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return course.equals(module.course) &&
                description.equals(module.description) &&
                quizzes.equals(module.quizzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, description, quizzes);
    }


}
