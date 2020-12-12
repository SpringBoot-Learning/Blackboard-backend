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
import springboot.postgresql.Blackboard.exception.AuthorisationException;
import springboot.postgresql.Blackboard.exception.ResourceNotFoundException;
import springboot.postgresql.Blackboard.model.Person;
import springboot.postgresql.Blackboard.repository.PersonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    //get persons
    @GetMapping("persons")
    public List<Person> getAllPersons() { return this.personRepository.findAll(); }

    //get person by id
    @GetMapping("persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") int personId)
            throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("person not found for this id :: " + personId));

        return ResponseEntity.ok().body(person);
    }

    //save person
    @PostMapping("persons")
    public Person createPerson(@RequestBody Person person) { return this.personRepository.save(person); }

    //update person
    @PutMapping(value = "person/{id}")
    public ResponseEntity<Person> updateEmployee(@PathVariable(value = "id") int personId, @Validated @RequestBody Person personDetails)
            throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("person not found for this id :: " + personId));

        person.setName(personDetails.getName());
        person.setAge(personDetails.getAge());
        person.setEmail(personDetails.getEmail());
        person.setAddress(personDetails.getAddress());
        person.setPhoneNo(personDetails.getPhoneNo());
        person.setPassword(personDetails.getPassword());
        person.setUsername(personDetails.getUsername());

        return ResponseEntity.ok(this.personRepository.save(person));
    }

    //delete person
    @DeleteMapping("person/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") int personId)
        throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id ::" + personId ));

        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //login
    @PostMapping("persons/login")
    public Map<String, Boolean> validLogin(@RequestBody Person person)
            throws AuthorisationException {
        Person person1 = Optional.ofNullable(personRepository.autenticatePerson(person.getUsername(), person.getPassword()))
                .orElseThrow(() -> new AuthorisationException("Login Failed, Please Try Again"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("LoggedIn", Boolean.TRUE);
        return response;
    }
}
