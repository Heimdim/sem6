package entity;

import java.io.Serializable;


public class Person implements Serializable
{
    private int id, height, age;
    private String name;
    private Gender gender,preferenceGender;

    private int lowPreferenceAge,highPreferenceAge;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public int getHeight()
    {
        return height;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public Gender getGender()
    {
        return gender;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Gender getPreferenceGender()
    {
        return preferenceGender;
    }
    public void setPreferenceGender(Gender preferenceGender)
    {
        this.preferenceGender = preferenceGender;
    }

    public int getLowPreferenceAge()
    {
        return lowPreferenceAge;
    }
    public void setLowPreferenceAge(int lowPreferenceAge)
    {
        this.lowPreferenceAge = lowPreferenceAge;
    }
    public int getHighPreferenceAge()
    {
        return highPreferenceAge;
    }
    public void setHighPreferenceAge(int highPreferenceAge)
    {
        this.highPreferenceAge = highPreferenceAge;
    }


    public Person(int age, int height,Gender preferenceGender, Gender gender,
                   int lowPreferenceAge, int highPreferenceAge, String name)
    {
        this.height = height;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.preferenceGender = preferenceGender;
        this.lowPreferenceAge = lowPreferenceAge;
        this.highPreferenceAge=highPreferenceAge;
    }

    @Override
    public String toString() {
        return "Person#" + id +
                ",name=" + name  +
                ",age=" + age +
                ",height=" + height +
                ",gender=" + gender +
                ",preferenceGender=" + preferenceGender +
                ",lowPreferenceAge=" + lowPreferenceAge +
                ",highPreferenceAge=" + highPreferenceAge;
    }
}
