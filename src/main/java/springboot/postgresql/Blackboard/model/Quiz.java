package springboot.postgresql.Blackboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.jws.WebParam;
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
public class Quiz {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;

    @ManyToOne
    @JsonIgnore
    private Module module;

    @OneToMany(mappedBy = "quiz", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(boolean status, Module module, List<Question> questions) {
        this.status = status;
        this.module = module;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return status == quiz.status &&
                module.equals(quiz.module) &&
                questions.equals(quiz.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, module, questions);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", status=" + status +
                ", module=" + module +
                ", questions=" + questions +
                '}';
    }
}
