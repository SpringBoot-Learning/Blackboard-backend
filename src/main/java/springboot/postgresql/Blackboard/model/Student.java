package springboot.postgresql.Blackboard.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Student extends Person{

   private int gradYear;

   @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
   private List<Enrollment> enrollments;

   @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
   private List<Answer> answers;

   public Student() {
   }

   public Student(String name, int age, String email, String address, long phoneNo, String password, String username, int gradYear, List<Enrollment> enrollments, List<Answer> answers) {
      super(name, age, email, address, phoneNo, password, username);
      this.gradYear = gradYear;
      this.enrollments = enrollments;
      this.answers = answers;
   }

   public int getGradYear() {
      return gradYear;
   }

   public void setGradYear(int gradYear) {
      this.gradYear = gradYear;
   }

   public List<Enrollment> getEnrollments() {
      return enrollments;
   }

   public void setEnrollments(List<Enrollment> enrollments) {
      this.enrollments = enrollments;
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
      if (!super.equals(o)) return false;
      Student student = (Student) o;
      return gradYear == student.gradYear &&
              enrollments.equals(student.enrollments) &&
              answers.equals(student.answers);
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), gradYear, enrollments, answers);
   }

   @Override
   public String toString() {
      return "Student{" +
              "gradYear=" + gradYear +
              ", enrollments=" + enrollments +
              ", answers=" + answers +
              '}';
   }
}
