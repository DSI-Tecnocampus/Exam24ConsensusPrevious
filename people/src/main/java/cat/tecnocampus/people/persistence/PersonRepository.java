package cat.tecnocampus.people.persistence;

import cat.tecnocampus.people.application.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String>{
}
