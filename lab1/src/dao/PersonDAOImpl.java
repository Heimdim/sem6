package dao;

import models.Gender;
import models.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAOImpl implements PersonDAO
{
    private static final String ADDING_RECORD = "INSERT INTO person_table VALUES(?,?,?,?,?,?,?,?)";
    private static final String DELETING_RECORD = "DELETE FROM person_table WHERE id = ?";
    private static final String SELECTING_RECORDS = "SELECT * FROM person_table JOIN gender_table " +
            "g ON (person_table.gender_id = g.gender_id AND person_table.preferenceGender_id=g.gender_id)" +
            "WHERE preferenceGender_id=?  AND lowPreferenceAge=? AND highPreferenceAge=?" ;

    private Connection con;
    private PreparedStatement prepSt1;

    @Override
    public boolean addPerson(Person person)
    {
       try
       {
           int genderID=(person.getGender().equals(Gender.MALE))?0:1;
           int preferenceGenderID=(person.getPreferenceGender().equals(Gender.MALE))?0:1;
           DBManager dbm=new DBManager();
           con=dbm.getConnection();
           prepSt1=con.prepareStatement(ADDING_RECORD);
           con.setAutoCommit(false);

           prepSt1.setInt(1,person.getId());
           prepSt1.setInt(2,person.getAge());
           prepSt1.setInt(3,person.getHeight());
           prepSt1.setInt(4,genderID);
           prepSt1.setInt(5,preferenceGenderID);
           prepSt1.setInt(6,person.getLowPreferenceAge());
           prepSt1.setInt(7,person.getHighPreferenceAge());
           prepSt1.setString(8,person.getName());
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
            prepSt1 = con.prepareStatement(DELETING_RECORD);
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
    public ArrayList<Person> getPersons(Gender preferenceGender,int lowPreferenceAge,
                                        int highPreferenceAge)
    {
        try
        {
            DBManager dbm = new DBManager();
            con = dbm.getConnection();
            prepSt1 = con.prepareStatement(SELECTING_RECORDS);
            prepSt1.setInt(1, (preferenceGender.equals(Gender.MALE))?0:1);
            prepSt1.setInt(2, lowPreferenceAge);
            prepSt1.setInt(3, highPreferenceAge);
            ResultSet buffRS = prepSt1.executeQuery();
            ArrayList<Person> buffLst = new ArrayList<>();
            while(buffRS.next())
            {
                int id = buffRS.getInt(1);
                int age=buffRS.getInt(2);
                int height=buffRS.getInt(3);;
                Gender prefGender=(buffRS.getInt(4)==0)?Gender.MALE:Gender.FEMALE;
                Gender gender=(buffRS.getInt(5)==0)?Gender.MALE:Gender.FEMALE;
                int lowPrefAge=buffRS.getInt(6);
                int highPrefAge=buffRS.getInt(7);
                String name=buffRS.getString(8);
                buffLst.add(new Person(id,age,height,prefGender,gender,lowPrefAge,highPrefAge,name));

                System.out.println(buffLst.size());
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
