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
import springboot.postgresql.Blackboard.model.Answer;
import springboot.postgresql.Blackboard.model.Choice;
import springboot.postgresql.Blackboard.model.Module;
import springboot.postgresql.Blackboard.model.MultipleChoice;
import springboot.postgresql.Blackboard.model.Question;
import springboot.postgresql.Blackboard.model.Quiz;
import springboot.postgresql.Blackboard.model.TrueFalse;
import springboot.postgresql.Blackboard.repository.ChoiceRepository;
import springboot.postgresql.Blackboard.repository.MultipleChoiceRepository;
import springboot.postgresql.Blackboard.repository.QuizRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;
    private MultipleChoiceRepository multipleChoiceRepository;
    private ChoiceRepository choiceRepository;

    @GetMapping("quizzes")
    public List<Quiz> getAlleQuizzs() { return this.quizRepository.findAll(); }

    //get course by id
    @GetMapping("quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable(value = "id") int quizId)
            throws ResourceNotFoundException {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("quiz not found for this id :: " + quizId));

        return ResponseEntity.ok().body(quiz);
    }

    //create true false question for quiz by id
    @PutMapping(value = "teacher/create/question/trueFalse/{id}")
    public ResponseEntity<Quiz> createTrueFalseQuestion(@PathVariable(value = "id") int quizId, @Validated @RequestBody TrueFalse trueFalseDetails)
            throws ResourceNotFoundException {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("quiz not found for this id :: " + quizId));

        TrueFalse trueFalse = new TrueFalse();
        trueFalse.setQuestionDescription(trueFalseDetails.getQuestionDescription());
        trueFalse.setTrue(trueFalseDetails.isTrue());
        trueFalse.setPoints(trueFalseDetails.getPoints());
        trueFalse.setQuiz(quiz);


        List<Question> questionList = quiz.getQuestions();
        questionList.add(trueFalse);
        quiz.setQuestions(questionList);

        return ResponseEntity.ok(this.quizRepository.save(quiz));
    }

    //create multipleChoice question for a quiz by id
    @PutMapping(value = "teacher/create/quiz/multipleChoice/{id}")
    public ResponseEntity<Quiz> createMultipleChoiceQuestion(@PathVariable(value = "id") int quizId, @Validated @RequestBody MultipleChoice multipleChoiceDetails)
            throws ResourceNotFoundException {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("quiz not found for this id :: " + quizId));

        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setPoints(multipleChoiceDetails.getPoints());
        multipleChoice.setQuiz(quiz);
        multipleChoice.setCorrectAnswer(multipleChoiceDetails.getCorrectAnswer());
        multipleChoice.setQuestionDescription(multipleChoiceDetails.getQuestionDescription());


        List<Question> questionList = quiz.getQuestions();
        questionList.add(multipleChoice);
        quiz.setQuestions(questionList);


        return ResponseEntity.ok(this.quizRepository.save(quiz));
    }
}
