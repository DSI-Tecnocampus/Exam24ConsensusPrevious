package cat.tecnocampus.people.frontApi;

import cat.tecnocampus.people.application.domain.Person;
import cat.tecnocampus.people.persistence.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonApiRest {
    private final PersonRepository personRepository;

    public PersonApiRest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/people")
    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/people/{email}")
    public Person getPerson(@PathVariable String email) {
        return personRepository.findById(email).orElse(null);
    }

    @GetMapping("/people/{email}/exists")
    public boolean existsPerson(@PathVariable String email) {
        return personRepository.existsById(email);
    }

    @PostMapping("/people")
    public void createPerson(@RequestBody Person person) {
        personRepository.save(person);
    }
}
