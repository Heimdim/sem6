package dao;

import com.sun.xml.internal.bind.api.JAXBRIContext;
import entity.Gender;
import entity.Person;
import entity.WrapPersons;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 Класс, реализующий интерфейс {@link PersonDAO},
 служит для взаимодействия с xml файлом,
 используя технологию JAXB

 @author Dmitriy Romanovets
 @version 1.0
 */
public class PersonDAOJAXBImpl implements PersonDAO
{
    /**Поле, хранящее путь к xml файлу, содержащему полный список людей     */
    private String xmlLocation = "ServerJAXB/src/xml_source/persons.xml";
    /**Поле, хранящее путь к xsd файлу, описывающему схему xml документа,
     * содержащего полный список людей*/
    private String xsdLocation="ServerJAXB/src/xml_source/persons.xsd";

    /**
     * Поле, содержащее JAXB контекст
     * @see JAXBContext
     */
    private JAXBContext context;

    /**
     * Конструктор класса, осуществляющий валидацию xml файла и получение контекста
     */
    public PersonDAOJAXBImpl()
    {
        try
        {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            File schemaLocation = new File(xsdLocation);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlLocation));
            validator.validate(source);
            context=JAXBContext.newInstance(WrapPersons.class);
            Marshaller marshaller = context.createMarshaller();
        }catch (IOException| SAXException| JAXBException ex)
        {
            System.out.println("XML file is not valid.");
            ex.printStackTrace();
        }

    }

    /**
     * Метод для добавления человека в xml файл, используя технологию JAXB
     *
     * @param person - добавляемый человек
     */
    @Override
    public boolean addPerson(Person person)
    {
        try
        {
            File file = new File(xmlLocation);
            ArrayList<Person> persons = (ArrayList<Person>) getPersons();
            person.setId(getMaxId(persons) + 1);
            persons.add(person);

            WrapPersons wrapPersons = new WrapPersons();
            wrapPersons.setPersons(persons);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapPersons, file);
        } catch (JAXBException e)
        {
          e.printStackTrace();
        }
        return true;
    }

    /**
     * Метод для нахождения максимального существующего идентификационного номера среди существующих людей
     * @param persons - полный список людей
     * @return возвращает максимальный существующий идентификационный номер
     */
    private int getMaxId(ArrayList<Person> persons)
    {
        int maxId = 0;

        for (Person person : persons)
        {
            int curId = person.getId();
            if (curId > maxId)
                maxId = curId;
        }
        return maxId;
    }

    /**
     * Метод для удаления заданного человека из xml файла, используя технологию JAXB
     *
     * @param person - удаляемый человек
     */
    @Override
    public boolean removePerson(Person person)
    {
        try {
            File file = new File(xmlLocation);
            ArrayList<Person> persons = (ArrayList<Person>) getPersons();
            persons.removeIf(receipt1 -> receipt1.getId() == person.getId());

            WrapPersons wrapPersons = new WrapPersons();
            wrapPersons.setPersons(persons);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapPersons, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Метод для обновления параметров заданного человека
     *
     * @param person1 - обновляемый человек
     * @param person2 - человек со внесенными изменениями
     */
    @Override
    public boolean updatePerson(Person person1, Person person2)
    {
        person2.setGender(person1.getGender());
        person2.setPreferenceGender(person1.getPreferenceGender());
        person2.setId(person1.getId());
        removePerson(person1);
        addPerson(person2);
        return false;
    }

    /**
     * Метод для поиска человека по его идентификационному номеру
     *
     * @param id - идентификационный номер
     * @return вовзращает человека с заданным идентификационным номером
     */
    @Override
    public Person getPersonById(int id)
    {
        List<Person> persons=getPersons();
        for (Person person:persons) {
            if(person.getId()==id)
                return person;
        }
        return  null;
    }

    /**
     * Метод для получения полного списка людей из xml файла, используя технологию JAXB
     *
     * @return возвращает полный список людей
     */
    @Override
    public List<Person> getPersons()
    {
        System.out.println("aaaa");
        WrapPersons wrapPersons = new WrapPersons();
        try
        {
            File file = new File(xmlLocation);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            wrapPersons = (WrapPersons) unmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return wrapPersons.getPersons();
    }

    /**
     * Метод для поиска подходящих по критерию партнеров в xml файле, используя технологию JAXB
     *
     * @param person - человек, для которого ищутся партнеры
     * @return возвращает список подходящих партнеров
     */
    @Override
    public List<Person> getSuitablePersons(Person person)
    {
        List<Person> persons=getPersons(), suitablePersons= new ArrayList<>();
        for (Person curPerson:persons)
        {
            Gender tempGender=curPerson.getGender();
            int tempAge=curPerson.getAge();
            if(tempGender==person.getPreferenceGender()
                    &&tempAge>=person.getLowPreferenceAge()&&tempAge<=person.getHighPreferenceAge())
                suitablePersons.add(curPerson);
        }
        return suitablePersons;
    }



}
