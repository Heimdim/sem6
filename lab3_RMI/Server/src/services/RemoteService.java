package services;

import entity.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteService extends Remote
{
    List<Person> getSuitablePersons(Person temp) throws RemoteException;
    List<Person> getPersons()throws RemoteException;
    void addPerson(Person temp)throws RemoteException;
    void removePerson(Person selectedItem)throws RemoteException;
    void updatePerson(Person curPerson, Person updatePerson)throws RemoteException;
}
