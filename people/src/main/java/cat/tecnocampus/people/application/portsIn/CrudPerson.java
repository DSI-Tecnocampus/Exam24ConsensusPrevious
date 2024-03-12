package cat.tecnocampus.people.application.portsIn;

import cat.tecnocampus.people.application.domain.Person;

import java.util.List;

public interface CrudPerson {

    public void createPerson(String email, String name, String surname, String title);
    public List<Person> getPeople();

    public Person getPerson(String email);
}
