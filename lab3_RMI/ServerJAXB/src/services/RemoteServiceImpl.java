package services;

import dao.PersonDAOJAXBImpl;
import entity.Person;

import java.rmi.RemoteException;
import java.util.List;

/**
 Класс, реализующий интерфейс {@link RemoteService},
 служит для вызова методов взаимодействия с базой данных XML средствами RMI

 @author Dmitriy Romanovets
 @version 1.0
 */

public class RemoteServiceImpl implements RemoteService
{
    /**Поле, храящее экземпляр класса {@link PersonDAOJAXBImpl}  */
    PersonDAOJAXBImpl pdi;

    /**
     * Конструктор класса, служащий для инициализации полей
     */
    public RemoteServiceImpl()
    {
        pdi=new PersonDAOJAXBImpl();
    }

    /**
     * Метод для поиска подходящих по критерию партнеров
     *
     * @param temp - человек, для которого ищутся партнеры
     * @return возвращает список подходящих партнеров
     * @throws RemoteException - выкидывается при возникновение ошибок, связанных с удаленным вызовом методов
     */
    @Override
    public List<Person> getSuitablePersons(Person temp) throws RemoteException {
        return pdi.getSuitablePersons(temp);
    }

    /**
     * Метод для получения полного списка людей
     *
     * @return вовзращает полный список людей
     */
    @Override
    public List<Person> getPersons() throws RemoteException {
        return pdi.getPersons();
    }

    /**
     * Метод для добавления человека
     *
     * @param temp - добавляемый человек
     * @throws RemoteException - выкидывается при возникновение ошибок, связанных с удаленным вызовом методов
     */
    @Override
    public void addPerson(Person temp) throws RemoteException {
        pdi.addPerson(temp);
    }

    /**
     * Метод для удаление заданного человека
     *
     * @param selectedItem - заданный для удаления человек
     * @throws RemoteException - выкидывается при возникновение ошибок, связанных с удаленным вызовом методов
     */
    @Override
    public void removePerson(Person selectedItem) throws RemoteException {
        pdi.removePerson(selectedItem);
    }

    /**
     * Метод для обновления заданного человека
     *
     * @param curPerson - обновляемый человек
     * @param updatePerson - человек со внесенными изменениями
     * @throws RemoteException - выкидывается при возникновение ошибок, связанных с удаленным вызовом методов
     */
    @Override
    public void updatePerson(Person curPerson, Person updatePerson) throws RemoteException {
        pdi.updatePerson(curPerson,updatePerson);
    }
}
