package springboot.postgresql.Blackboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.postgresql.Blackboard.exception.ResourceNotFoundException;
import springboot.postgresql.Blackboard.model.Choice;
import springboot.postgresql.Blackboard.model.MultipleChoice;
import springboot.postgresql.Blackboard.model.Question;
import springboot.postgresql.Blackboard.model.Quiz;
import springboot.postgresql.Blackboard.model.TrueFalse;
import springboot.postgresql.Blackboard.repository.MultipleChoiceRepository;
import springboot.postgresql.Blackboard.repository.QuestionRepository;
import springboot.postgresql.Blackboard.repository.TrueFalseRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    private TrueFalseRepository trueFalseRepository;
    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;

    //create choices for multipleChoice by id
    @PutMapping(value = "teacher/create/choices/{id}")
    public ResponseEntity<MultipleChoice> createChoices(@PathVariable(value = "id") int multipleChoiceId, @Validated @RequestBody Choice choiceDetails)
            throws ResourceNotFoundException {
        MultipleChoice multipleChoice = (MultipleChoice) questionRepository.findById(multipleChoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Multiple Choice not found for this id :: " + multipleChoiceId));
        List<Choice> choicesList = multipleChoice.getChoices();

        Choice choice = new Choice();
        choice.setLabel(choiceDetails.getLabel());
        choice.setMultipleChoice(multipleChoice);
        choicesList.add(choice);


        multipleChoice.setChoices(choicesList);

        return ResponseEntity.ok(this.multipleChoiceRepository.save(multipleChoice));
    }


}
