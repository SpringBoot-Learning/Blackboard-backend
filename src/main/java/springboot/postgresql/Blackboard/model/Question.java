package springboot.postgresql.Blackboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xerces.internal.xinclude.XPointerSchema;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Inheritance( strategy = InheritanceType.SINGLE_TABLE)
public class Question {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private int points;

    private String questionDescription;

    @ManyToOne
    @JsonIgnore
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Answer> answers;

    public Question() {
    }

    public Question(int points, String questionDescription, Quiz quiz, List<Answer> answers) {
        this.points = points;
        this.questionDescription = questionDescription;
        this.quiz = quiz;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                points == question.points &&
                questionDescription.equals(question.questionDescription) &&
                quiz.equals(question.quiz) &&
                answers.equals(question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, questionDescription, quiz, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", points=" + points +
                ", questionDescription='" + questionDescription + '\'' +
                ", quiz=" + quiz +
                ", answers=" + answers +
                '}';
    }
}
