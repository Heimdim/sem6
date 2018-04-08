package dao;

import entity.Person;

import java.util.List;

/**
  Интерфейс для работы с людьми

 @author Dmitriy Romanovets
 @version 1.0
 */
public interface PersonDAO
{
    /**
     * Метод для добавления нового чеовека
     *
     * @param person - добавляемый человек
     */
    boolean addPerson(Person person);

    /**
     * Метод для удаления заданного человека
     *
     * @param person - удаляемый человек
     */
    boolean removePerson(Person person);

    /**
     * Метод для обновления заданного человека
     *
     * @param person1 - обнолвяемый человек
     * @param person2 - человек со внесенными изменениями
     */
    boolean updatePerson(Person person1, Person person2);

    /**
     * Метод для поиска человека по его идентификационному номеру
     *
     * @param id - идентификационный номер
     * @return вовзращает человека с заданным идентификационным номером
     */
    Person getPersonById(int id);

    /**
     * Метод для получения полного списка людей
     *
     * @return вовзращает полный список людей
     */
    List<Person> getPersons();

    /**
     * Метод для поиска подходящих по критерию партнеров
     *
     * @param person - человек, для которого ищутся партнеры
     * @return возвращает список подходящих партнеров
     */
    List<Person> getSuitablePersons(Person person);
}
