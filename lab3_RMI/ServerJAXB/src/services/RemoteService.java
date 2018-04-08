package services;

import entity.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 <p>Интерфейс удаленного сервиса, для реализации
 * вызова методов с помощью библиотеки <tt><a href="https://ru.wikipedia.org/wiki/RMI">RMI</a></tt>.

 @author Dmitriy Romanovets
 @version 1.0
 */


public interface RemoteService extends Remote
{
    /**
     * Метод для поиска подходящих по критерию партнеров
     *
     * @param temp - человек, для которого ищутся партнеры
     * @return возвращает список подходящих партнеров
     */
    List<Person> getSuitablePersons(Person temp) throws RemoteException;

    /**
     * Метод для получения полного списка людей
     *
     * @return вовзращает полный список людей
     */
    List<Person> getPersons()throws RemoteException;

    /**
     * Метод для добавления человека
     *
     * @param temp - добавляемый человек
     * @throws RemoteException
     */
    void addPerson(Person temp)throws RemoteException;

    /**
     * Метод для удаление заданного человека
     *
     * @param selectedItem - заданный для удаления человек
     * @throws RemoteException
     */
    void removePerson(Person selectedItem)throws RemoteException;

    /**
     * Метод для обновления заданного человека
     *
     * @param curPerson - обновляемый человек
     * @param updatePerson - человек со внесенными изменениями
     * @throws RemoteException
     */
    void updatePerson(Person curPerson, Person updatePerson)throws RemoteException;
}
