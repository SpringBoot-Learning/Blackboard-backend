package springboot.postgresql.Blackboard.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class MultipleChoice extends Question {

    private int correctAnswer;

    @OneToMany(mappedBy = "multipleChoice", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Choice> choices;

    public MultipleChoice() {
    }

    public MultipleChoice(int points, String questionDescription, Quiz quiz, List<Answer> answers, int correctAnswer, List<Choice> choices) {
        super(points, questionDescription, quiz, answers);
        this.correctAnswer = correctAnswer;
        this.choices = choices;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MultipleChoice that = (MultipleChoice) o;
        return correctAnswer == that.correctAnswer &&
                choices.equals(that.choices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), correctAnswer, choices);
    }

}
