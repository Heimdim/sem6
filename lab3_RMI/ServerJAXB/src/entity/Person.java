package entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 Класс человека со свойствами <b>id</b>, <b>age</b>, <b>height</b>, <b>name</b>, <b>gender</b>, <b>preferenceGender</b>,
 <b>lowPreferenceAge</b>, <b>highPreferenceAge</b>

 @author Dmitriy Romanovets
 @version 1.0
 */

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable
{
    /**Поле, хранящее идентификационный номер     */
    @XmlAttribute(required = true)
    private int id;
    /**Поле, хранящее возраст    */
    @XmlElement(name = "age")
    private int  age;
    /**Поле, хранящее рост     */
    @XmlElement(name = "height")
    private int height;
    /**Поле, хранящее имя     */
    @XmlElement(name = "name")
    private String name;
    /**Поле, хранящее гендер    */
    @XmlElement(name = "gender")
    private Gender gender;
    /**Поле, хранящее предпочитаемый гендер партнера     */
    @XmlElement(name = "prefgender")
    private Gender preferenceGender;
    /**Поле, хранящее нижнюю границу предпочитаемого возраста партнера     */
    @XmlElement(name = "hprefage")
    private int highPreferenceAge;
    /**Поле, ххранящее верхнюю границу предпочитаемого возраста партнера    */
    @XmlElement(name = "lprefage")
    private int  lowPreferenceAge;

    /**
     * Метод для получения идентификационного номера
     *
     * @return вовзращает идентификационный номер
     */
    public int getId()
    {
        return id;
    }

    /**
     * Метод для установления идентификационного номера
     *
     * @param id - устанавливаемый идентификационный номер
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Метод для получения роста
     *
     * @return вовзращает рост
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Метод для установления роста
     *
     * @param height - устанавливаемый рост
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Метод для получения возраста
     *
     * @return вовзращает возраст
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Метод для установления возраста
     *
     * @param age - устанавливаемый возраст
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Метод для получения имени
     *
     * @return вовзращает имя
     */
    public String getName()
    {
        return name;
    }

    /**
     * Метод для установления имени
     *
     * @param name - устанавливаемое имя
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Метод для получения гендера
     *
     * @return вовзращает гендер
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * Метод для установления гендера
     *
     * @param gender - устанавливаемый гендер
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * Метод для получения предпочитаемого гендера партнера
     *
     * @return вовзращает предпочитаемый гендер партнера
     */
    public Gender getPreferenceGender()
    {
        return preferenceGender;
    }

    /**
     * Метод для установления предпочитаемого гендера партнера
     *
     * @param preferenceGender - устанавливаемый предпочитаемый гендер партнера
     */
    public void setPreferenceGender(Gender preferenceGender)
    {
        this.preferenceGender = preferenceGender;
    }

    /**
     * Метод для получения нижней границы предпочитаемого возраста партнера
     *
     * @return вовзращает нижнюю границу предпочитаемого возраста партнера
     */
    public int getLowPreferenceAge()
    {
        return lowPreferenceAge;
    }

    /**
     * Метод для установления нижней границы предпочитаемого возраста партнера
     *
     * @param lowPreferenceAge - устанавливаемая нижняя граница предпочитаемого возраста партнера
     */
    public void setLowPreferenceAge(int lowPreferenceAge)
    {
        this.lowPreferenceAge = lowPreferenceAge;
    }

    /**
     * Метод для получения верхней границы предпочитаемого возраста партнера
     *
     * @return вовзращает верхнюю границу предпочитаемого возраста партнера
     */
    public int getHighPreferenceAge()
    {
        return highPreferenceAge;
    }

    /**
     * Метод для установления верхней границы предпочитаемого возраста партнера
     *
     * @param highPreferenceAge - устанавливаемая верхняя граница предпочитаемого возраста партнера
     */
    public void setHighPreferenceAge(int highPreferenceAge)
    {
        this.highPreferenceAge = highPreferenceAge;
    }

    /**
     * Конструктор, создающий объект нового человека по заданным параметрам
     *
     * @param age - задаваемый возраст
     * @param height - задаваемый рост
     * @param preferenceGender - задаваемый предпочитаемый гендер партнера
     * @param gender - задаваемый гендер
     * @param lowPreferenceAge - задаваемая нижняя граница предпочитаемого возраста партнера
     * @param highPreferenceAge - задаваемая верхняя граница предпочитаемого возраста партнера
     * @param name - задаваемое имя
     */
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

    /**
     * Метод, перегружающий метод toString класса {@link Object#toString()}
     *
     * @return возвращает текстовую строку с описанием человека
     */
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

    /**
     * Конструктор по умолчанию
     */
    public Person()
    {
    }
}
