package services;

import dao.PersonDAOImpl;
import entity.Person;

import java.rmi.RemoteException;
import java.util.List;

public class RemoteServiceImpl implements RemoteService
{
    PersonDAOImpl pdi;


    public RemoteServiceImpl()
    {
        pdi=new PersonDAOImpl();
    }

    @Override
    public List<Person> getSuitablePersons(Person temp) throws RemoteException {
        return pdi.getSuitablePersons(temp);
    }

    @Override
    public List<Person> getPersons() throws RemoteException {
        return pdi.getPersons();
    }

    @Override
    public void addPerson(Person temp) throws RemoteException {
        pdi.addPerson(temp);
    }

    @Override
    public void removePerson(Person selectedItem) throws RemoteException {
        pdi.removePerson(selectedItem);
    }

    @Override
    public void updatePerson(Person curPerson, Person updatePerson) throws RemoteException {
        pdi.updatePerson(curPerson,updatePerson);
    }
}
