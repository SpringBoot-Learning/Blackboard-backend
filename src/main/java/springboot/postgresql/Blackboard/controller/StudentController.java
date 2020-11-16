package springboot.postgresql.Blackboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.postgresql.Blackboard.exception.ResourceNotFoundException;
import springboot.postgresql.Blackboard.model.Person;
import springboot.postgresql.Blackboard.model.Student;
import springboot.postgresql.Blackboard.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    //get all students
    @GetMapping("students")
    public List<Student> getAllStudents() { return this.studentRepository.findAll(); }

    //get student by id
    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") int studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentId));

        return ResponseEntity.ok().body(student);
    }

    //save person
    @PostMapping("students")
    public Student createStudent(@RequestBody Student student) { return this.studentRepository.save(student); }

    //update student
    @PutMapping(value = "student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") int studentId, @Validated @RequestBody Student studentDetails)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentId));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());
        student.setPhoneNo(studentDetails.getPhoneNo());
        student.setPassword(studentDetails.getPassword());
        student.setUsername(studentDetails.getUsername());
        student.setAnswers(studentDetails.getAnswers());
        student.setEnrollments(studentDetails.getEnrollments());
        student.setGradYear(studentDetails.getGradYear());

        return ResponseEntity.ok(this.studentRepository.save(student));
    }

}
