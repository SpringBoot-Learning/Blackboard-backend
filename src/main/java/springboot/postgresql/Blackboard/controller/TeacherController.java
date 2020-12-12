package springboot.postgresql.Blackboard.controller;

import com.sun.org.apache.bcel.internal.generic.DCONST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.postgresql.Blackboard.exception.ResourceNotFoundException;
import springboot.postgresql.Blackboard.model.Course;
import springboot.postgresql.Blackboard.model.Student;
import springboot.postgresql.Blackboard.model.Teacher;
import springboot.postgresql.Blackboard.repository.CourseRepository;
import springboot.postgresql.Blackboard.repository.StudentRepository;
import springboot.postgresql.Blackboard.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

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


    //create course
    @CrossOrigin
    @PutMapping(value = "teacher/create/course/{id}")
    public ResponseEntity<Teacher> createCourse(@PathVariable(value = "id") int teacherId, @Validated @RequestBody Course courseDetails)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found for this id :: " + teacherId));

       Course course = new Course();
       course.setLabel(courseDetails.getLabel());
       course.setTeacher(teacher);

       List<Course> courseList = teacher.getCourses();
       courseList.add(course);
       teacher.setCourses(courseList);
       this.teacherRepository.save(teacher);


       return ResponseEntity.ok(this.teacherRepository.save(teacher));
    }

    //save person
    @PostMapping("teacher/create/students")
    public Student createStudent(@RequestBody Student studentDetails) {
        String[] names = studentDetails.getName().split(" ");
        String username = names[0].toLowerCase().charAt(0) + names[1].toLowerCase();
        String password = "qwerty";
        studentDetails.setUsername(username);
        studentDetails.setPassword(password);
        return this.studentRepository.save(studentDetails);
    }

    //get courses teacher by id
    @GetMapping("teachers/courses/{id}")
    public ResponseEntity<List<Course>> getCoursesByTeacherId(@PathVariable(value = "id") int teacherId)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found for this id :: " + teacherId));

        return ResponseEntity.ok().body(teacher.getCourses());
    }



}
