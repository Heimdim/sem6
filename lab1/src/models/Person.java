package models;

import java.io.Serializable;

public class Person implements Serializable
{
    private int id, height, age;
    private String name;
    private Gender gender,preferenceGender;
    private Borders preferenceHeight,preferenceAge;

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

    public Borders getPreferenceHeight()
    {
        return preferenceHeight;
    }
    public void setPreferenceHeight(Borders preferenceHeight)
    {
        this.preferenceHeight = preferenceHeight;
    }

    public Borders getPreferenceAge()
    {
        return preferenceAge;
    }
    public void setPreferenceAge(Borders preferenceAge)
    {
        this.preferenceAge = preferenceAge;
    }

    public Person(int id, int height, int age, String name, Gender gender,
                  Gender preferenceGender, Borders preferenceHeight, Borders preferenceAge)
    {
        this.id = id;
        this.height = height;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.preferenceGender = preferenceGender;
        this.preferenceHeight = preferenceHeight;
        this.preferenceAge = preferenceAge;
    }
}
