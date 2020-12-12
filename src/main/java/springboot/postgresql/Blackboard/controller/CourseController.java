package springboot.postgresql.Blackboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.postgresql.Blackboard.exception.ResourceNotFoundException;
import springboot.postgresql.Blackboard.model.Course;
import springboot.postgresql.Blackboard.model.Module;
import springboot.postgresql.Blackboard.model.Teacher;
import springboot.postgresql.Blackboard.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    //get all courses
    @GetMapping("courses")
    public List<Course> getCourses() { return this.courseRepository.findAll(); }

    //get course by id
    @GetMapping("courses/{id}")
    public ResponseEntity<Course> geCourseById(@PathVariable(value = "id") int courseId)
            throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("course not found for this id :: " + courseId));

        return ResponseEntity.ok().body(course);
    }

    //get course by label
    @GetMapping("courses/label/{label}")
    public ResponseEntity<Course> getCourseByLabel(@PathVariable(value = "label") String label)
            throws ResourceNotFoundException {
        Course course = Optional.ofNullable(courseRepository.findCourseByLabel(label))
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with label ::" + label));

        return ResponseEntity.ok().body(course);
    }

    //create module for a course by id
    @PutMapping(value = "teacher/create/module/{id}")
    public ResponseEntity<Course> createModule(@PathVariable(value = "id") int courseId, @Validated @RequestBody Module moduleDetails)
            throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("course not found for this id :: " + courseId));


        Module module = new Module();
        module.setLabel(moduleDetails.getLabel());
        module.setDescription(moduleDetails.getDescription());
        module.setCourse(course);

        List<Module> moduleList = course.getModules();
        moduleList.add(module);
        course.setModules(moduleList);

        return ResponseEntity.ok(this.courseRepository.save(course));
    }

}
