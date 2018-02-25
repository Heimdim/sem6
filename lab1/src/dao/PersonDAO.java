package dao;

import models.Gender;
import models.Person;

import java.util.ArrayList;

public interface PersonDAO
{
    boolean addPerson(Person person);

    boolean removePerson(Person person);

    ArrayList<Person> getPersons(Gender preferenceGender, int preferenceHeight, int preferenceAge);
}
