package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 Класс-обертка для списка людей

 @author Dmitriy Romanovets
 @version 1.0
 */


@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.NONE)
public class WrapPersons implements Serializable
{
    /** Поле, хранящее полный список людей */
    @XmlElement(name = "person")
    private List<Person> persons;

    /**
     * Метод для получения полного списка людей
     *
     * @return возвращает полный список людей
     */
    public List<Person> getPersons()
    {
            return persons;
    }

    /**
     * Метод для задания полного списка людей
     *
     * @param persons - задаваемый полный список людей
     */
    public void setPersons(List<Person> persons)
    {
        this.persons = persons;
    }
}

