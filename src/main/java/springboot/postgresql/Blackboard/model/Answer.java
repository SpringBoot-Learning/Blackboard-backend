package springboot.postgresql.Blackboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Answer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JsonIgnore
    private Question question;

    private boolean trueFalseAnswer;
    private int multipleChoiceAnswer;

    public Answer() {
    }

    public Answer(Student student, Question question, boolean trueFalseAnswer, int multipleChoiceAnswer) {
        this.student = student;
        this.question = question;
        this.trueFalseAnswer = trueFalseAnswer;
        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isTrueFalseAnswer() {
        return trueFalseAnswer;
    }

    public void setTrueFalseAnswer(boolean trueFalseAnswer) {
        this.trueFalseAnswer = trueFalseAnswer;
    }

    public int getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public void setMultipleChoiceAnswer(int multipleChoiceAnswer) {
        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return trueFalseAnswer == answer.trueFalseAnswer &&
                multipleChoiceAnswer == answer.multipleChoiceAnswer &&
                student.equals(answer.student) &&
                question.equals(answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, question, trueFalseAnswer, multipleChoiceAnswer);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", student=" + student +
                ", question=" + question +
                ", trueFalseAnswer=" + trueFalseAnswer +
                ", multipleChoiceAnswer=" + multipleChoiceAnswer +
                '}';
    }
}
