package dao;

import entity.Gender;
import entity.Person;
import utils.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO
{
    public static final String ADD_RECORD = "INSERT INTO person_table (age, height, preferenceGender_id, gender_id, lowPreferenceAge, highPreferenceAge, name) VALUES(?,?,?,?,?,?,?)";
    public static final String DELETE_RECORD = "DELETE FROM person_table WHERE id = ?";
    public static final String GET_ALL_RECORDS = "SELECT * FROM person_table p";
    public static final String UPDATE_RECORD="UPDATE person_table SET age=?, height=?, preferenceGender_id=?, gender_id=?," +
            "lowPreferenceAge=?, highPreferenceAge=?, name=? WHERE id=?";
    public static final String CHOICE_RECORD="SELECT * FROM person_table p JOIN gender_table g ON p.gender_id = g.gender_id WHERE p.gender_id=?  AND p.age>=? AND p.age<=?";
    public static final String GET_BY_ID_RECORD="SELECT * FROM person_table WHERE id=?";
//

    private Connection con;
    private PreparedStatement prepSt1;

    @Override
    public boolean addPerson(Person person)
    {
       try
       {
           int genderID=(person.getGender().equals(Gender.MALE))?1:2;
           int preferenceGenderID=(person.getPreferenceGender().equals(Gender.MALE))?1:2;
           DBManager dbm=new DBManager();
           con=dbm.getConnection();
           prepSt1=con.prepareStatement(ADD_RECORD);
           con.setAutoCommit(false);

           prepSt1.setInt(1,person.getAge());
           prepSt1.setInt(2,person.getHeight());
           prepSt1.setInt(3,preferenceGenderID);
           prepSt1.setInt(4,genderID);
           prepSt1.setInt(5,person.getLowPreferenceAge());
           prepSt1.setInt(6,person.getHighPreferenceAge());
           prepSt1.setString(7,person.getName());
           prepSt1.executeUpdate();

           con.commit();
           con.setAutoCommit(true);
           return  true;
       }
       catch (SQLException e)
       {
           try
           {
               con.rollback();
               con.setAutoCommit(true);
           }
           catch (SQLException e1)
           {
               e1.printStackTrace();
           }
           e.printStackTrace();
           return false;
       }
       finally
       {
           try
           {
               con.close();
               prepSt1.close();
           }
           catch (SQLException e)
           {
               e.printStackTrace();
           }
       }
    }

    @Override
    public boolean removePerson(Person person)
    {
        try
        {
            DBManager dbm = new DBManager();
            con = dbm.getConnection();
            prepSt1 = con.prepareStatement(DELETE_RECORD);
            prepSt1.setInt(1, person.getId());
            prepSt1.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                prepSt1.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updatePerson(Person person1,Person person2)
    {
        try
        {
            int genderID=(person2.getGender().equals(Gender.MALE))?1:2;
            int preferenceGenderID=(person2.getPreferenceGender().equals(Gender.MALE))?1:2;
            DBManager dbm = new DBManager();
            con = dbm.getConnection();
            prepSt1 = con.prepareStatement(UPDATE_RECORD);

            prepSt1.setInt(1,person2.getAge());
            prepSt1.setInt(2,person2.getHeight());
            prepSt1.setInt(3,preferenceGenderID);
            prepSt1.setInt(4,genderID);
            prepSt1.setInt(5,person2.getLowPreferenceAge());
            prepSt1.setInt(6,person2.getHighPreferenceAge());
            prepSt1.setString(7,person2.getName());
            prepSt1.setInt(8,person1.getId());
            prepSt1.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                prepSt1.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public Person getPersonById(int id)
    {
        Person temp=null;
        try
        {
            DBManager dbm = new DBManager();
            con = dbm.getConnection();
            prepSt1 = con.prepareStatement(GET_BY_ID_RECORD);
            prepSt1.setInt(1, id);
            ResultSet buffRS = prepSt1.executeQuery();

            buffRS.next();
            int age=buffRS.getInt(2);
            int height=buffRS.getInt(3);;
            Gender prefGender=(buffRS.getInt(4)==1)?Gender.MALE:Gender.FEMALE;
            Gender gender=(buffRS.getInt(5)==1)?Gender.MALE:Gender.FEMALE;
            int lowPrefAge=buffRS.getInt(6);
            int highPrefAge=buffRS.getInt(7);
            String name=buffRS.getString(8);

            temp=new Person(age,height,prefGender,gender,lowPrefAge,highPrefAge,name);
            temp.setId(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                prepSt1.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return temp;
    }

    @Override
    public List<Person> getPersons()
    {
        try
        {
            DBManager dbm = new DBManager();
            con = dbm.getConnection();
            prepSt1 = con.prepareStatement(GET_ALL_RECORDS);
            ResultSet buffRS = prepSt1.executeQuery();
            List<Person> buffLst = new ArrayList<>();
            while(buffRS.next())
            {
                int id = buffRS.getInt(1);
                int age=buffRS.getInt(2);
                int height=buffRS.getInt(3);;
                Gender prefGender=(buffRS.getInt(4)==1)?Gender.MALE:Gender.FEMALE;
                Gender gender=(buffRS.getInt(5)==1)?Gender.MALE:Gender.FEMALE;
                int lowPrefAge=buffRS.getInt(6);
                int highPrefAge=buffRS.getInt(7);
                String name=buffRS.getString(8);

                Person temp=new Person(age,height,prefGender,gender,lowPrefAge,highPrefAge,name);
                temp.setId(id);
                buffLst.add(temp);

            }
            return buffLst;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> getSuitablePersons(Person person)
    {
        try
        {
            DBManager dbm = new DBManager();
            int preferenceGenderID=(person.getPreferenceGender().equals(Gender.MALE))?1:2;
            con = dbm.getConnection();
            prepSt1 = con.prepareStatement(CHOICE_RECORD);
            prepSt1.setInt(1,preferenceGenderID);
            prepSt1.setInt(2,person.getLowPreferenceAge());
            prepSt1.setInt(3,person.getHighPreferenceAge());
            ResultSet buffRS = prepSt1.executeQuery();
            List<Person> buffLst = new ArrayList<>();
            while(buffRS.next())
            {
                int id = buffRS.getInt(1);
                String name=buffRS.getString(8);
                int age=buffRS.getInt(2);
                int height=buffRS.getInt(3);;
                int lowPrefAge=buffRS.getInt(6);
                int highPrefAge=buffRS.getInt(7);
                Gender gender=(buffRS.getInt(4)==1)?Gender.MALE:Gender.FEMALE;
                Gender prefGender=(buffRS.getInt(5)==1)?Gender.MALE:Gender.FEMALE;

                Person temp=new Person(age,height,prefGender,gender,lowPrefAge,highPrefAge,name);
                temp.setId(id);
                buffLst.add(temp);
            }
            return buffLst;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
