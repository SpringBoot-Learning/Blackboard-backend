package springboot.postgresql.Blackboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.postgresql.Blackboard.exception.ResourceNotFoundException;
import springboot.postgresql.Blackboard.model.Course;
import springboot.postgresql.Blackboard.model.Module;
import springboot.postgresql.Blackboard.model.Person;
import springboot.postgresql.Blackboard.model.Quiz;
import springboot.postgresql.Blackboard.repository.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;

    //get modules
    @GetMapping("modules")
    public List<Module> getAllModules() { return this.moduleRepository.findAll(); }

    //get course by id
    @GetMapping("modules/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable(value = "id") int moduleId)
            throws ResourceNotFoundException {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("module not found for this id :: " + moduleId));

        return ResponseEntity.ok().body(module);
    }

    //get course by label
    @GetMapping("modules/label/{label}")
    public ResponseEntity<List<Module>> getCourseByLabel(@PathVariable(value = "label") String label)
            throws ResourceNotFoundException {
        List<Module> module = Optional.ofNullable(moduleRepository.findModuleByLabel(label))
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with label ::" + label));

        return ResponseEntity.ok().body(module);
    }

    //create quiz for a module by id
    @PutMapping(value = "teacher/create/quiz/{id}")
    public ResponseEntity<Module> createQuiz(@PathVariable(value = "id") int moduleId, @Validated @RequestBody Quiz quizDetails)
            throws ResourceNotFoundException {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("module not found for this id :: " + moduleId));

        Quiz quiz = new Quiz();
        quiz.setStatus(quizDetails.isStatus());
        quiz.setModule(module);

        List<Quiz> quizList = module.getQuizzes();
        quizList.add(quiz);
        module.setQuizzes(quizList);

        return ResponseEntity.ok(this.moduleRepository.save(module));
    }
}
