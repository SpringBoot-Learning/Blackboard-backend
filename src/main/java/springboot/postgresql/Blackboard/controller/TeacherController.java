package springboot.postgresql.Blackboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.postgresql.Blackboard.exception.ResourceNotFoundException;
import springboot.postgresql.Blackboard.model.Student;
import springboot.postgresql.Blackboard.model.Teacher;
import springboot.postgresql.Blackboard.repository.TeacherRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    //get all teachers
    @GetMapping("teachers")
    public List<Teacher> getAllTeachers() { return this.teacherRepository.findAll(); }

    //get teacher by id
    @GetMapping("teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") int teacherId)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found for this id :: " + teacherId));

        return ResponseEntity.ok().body(teacher);
    }

    //save teacher
    @PostMapping("teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) { return this.teacherRepository.save(teacher); }

    //update Teacher
    @PutMapping(value = "teacher/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") int teacherId, @Validated @RequestBody Teacher teacherDetails)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found for this id :: " + teacherId));

        teacher.setName(teacherDetails.getName());
        teacher.setAge(teacherDetails.getAge());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setAddress(teacherDetails.getAddress());
        teacher.setPhoneNo(teacherDetails.getPhoneNo());
        teacher.setPassword(teacherDetails.getPassword());
        teacher.setUsername(teacherDetails.getUsername());
        teacher.setCourses(teacherDetails.getCourses());

        return ResponseEntity.ok(this.teacherRepository.save(teacher));
    }

}
