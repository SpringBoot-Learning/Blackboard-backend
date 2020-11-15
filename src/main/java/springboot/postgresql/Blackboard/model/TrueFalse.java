package springboot.postgresql.Blackboard.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class TrueFalse extends Question {

    private boolean isTrue;

    public TrueFalse() {
    }

    public TrueFalse(int points, String questionDescription, Quiz quiz, List<Answer> answers, boolean isTrue) {
        super(points, questionDescription, quiz, answers);
        this.isTrue = isTrue;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TrueFalse trueFalse = (TrueFalse) o;
        return isTrue == trueFalse.isTrue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isTrue);
    }

    @Override
    public String toString() {
        return "TrueFalse{" +
                "isTrue=" + isTrue +
                '}';
    }
}
