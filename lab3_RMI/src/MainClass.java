import dao.PersonDAOImpl;
import entity.Gender;
import entity.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws SQLException
    {
        PersonDAOImpl personDAO=new PersonDAOImpl();
        List<Person> list= personDAO.getPersons();
        for (Person p:list)
        {
            System.out.println(p.toString());
        }
        System.out.println("Enter id for remove");
        Scanner scanner=new Scanner(System.in);
        int removeID=scanner.nextInt();
       // personDAO.removePerson(removeID);
        Person person=personDAO.getPersonById(removeID);
        list=personDAO.getSuitablePersons(person);
        if(list.size()==0)
            System.out.println("aaaaa");
        for (Person p:list)
        {
            System.out.println(p.toString());
        }

    }
}
