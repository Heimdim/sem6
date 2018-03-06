package dao;

import entity.Person;

import java.util.List;

public interface PersonDAO
{
    boolean addPerson(Person person);

    boolean removePerson(Person person);

    boolean updatePerson(Person person1,Person person2);

    Person getPersonById(int id);

    List<Person> getPersons();

    List<Person> getSuitablePersons(Person person);
}
