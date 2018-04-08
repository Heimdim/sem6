package dao;

import entity.Gender;
import entity.Person;
import org.xml.sax.SAXException;

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


public class PersonDAOImpl implements PersonDAO
{
    private String xmlLocation = "ServerStAX/xml_source/persons.xml";
    private String xsdLocation="ServerStAX/xml_source/persons.xsd";
    private XMLInputFactory inputFactory;
    private XMLOutputFactory outputFactory;
    private XMLEventFactory eventFactory;
    private XMLEventWriter writer;
    private XMLEventReader reader;

    public PersonDAOImpl()
    {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File(xsdLocation);

        Schema schema;
        try {
            schema = factory.newSchema(schemaLocation);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        Validator validator = schema.newValidator();
        Source source = new StreamSource(new File(xmlLocation));
        try {
            validator.validate(source);
            System.out.println("PersonDAOImpl Created");
        } catch (SAXException | IOException e) {
            System.out.println("XML file is not valid.");
            throw new RuntimeException(e);
        }

        inputFactory = XMLInputFactory.newInstance();
        outputFactory = XMLOutputFactory.newInstance();
        eventFactory = XMLEventFactory.newInstance();
    }

    @Override
    public boolean addPerson(Person person)
    {
        int maxElementNumber=0;
        try
        {
            reader = inputFactory.createXMLEventReader(new FileReader(xmlLocation));
            writer = outputFactory.createXMLEventWriter(new FileWriter(xmlLocation));
            while (reader.hasNext())
            {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType())
                {
                    case XMLEvent.START_ELEMENT:
                        StartElement startElement1 = event.asStartElement();
                        String tagName1 = startElement1.getName().getLocalPart();
                        switch (tagName1)
                        {
                            case "person":
                                Attribute attribute = startElement1.getAttributeByName(QName.valueOf("id"));
                                int buf = Integer.valueOf(attribute.getValue());
                                System.out.println("buf: " + buf);
                                if (buf > maxElementNumber)
                                    maxElementNumber = buf;
                                break;
                            default:
                                break;
                        }
                        break;
                    case XMLEvent.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String tagName = endElement.getName().getLocalPart();
                        switch (tagName)
                        {
                            case "persons":
                                person.setId(maxElementNumber + 1);
                                writePerson(person);
                                writeSimpleTag("persons", false);
                                return false;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                writer.add(event);
            }
        } catch (XMLStreamException | IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return true;
    }

    private void writePerson(Person person) throws XMLStreamException {
        XMLEvent newLineSequence = eventFactory.createCharacters("\n");
        XMLEvent tabSequence = eventFactory.createCharacters("\t");
        writer.add(tabSequence);
        writeSimpleTag("psns:person", true);
        XMLEvent event = null;
        event = eventFactory.createAttribute("id", Integer.toString(person.getId()));
        writer.add(event);
        writer.add(newLineSequence);
        writeElement("psns:age",String.valueOf(person.getAge()),"\t\t\t");
        writer.add(newLineSequence);
        writeElement("psns:height",String.valueOf(person.getHeight()),"\t\t\t");
        writer.add(newLineSequence);
        writeElement("psns:prefgender",String.valueOf(person.getPreferenceGender()),"\t\t\t");
        writer.add(newLineSequence);
        writeElement("psns:gender", String.valueOf(person.getGender()),"\t\t\t");
        writer.add(newLineSequence);
        writeElement("psns:lprefage",String.valueOf(person.getLowPreferenceAge()),"\t\t\t");
        writer.add(newLineSequence);
        writeElement("psns:hprefage",String.valueOf(person.getHighPreferenceAge()),"\t\t\t");
        writer.add(newLineSequence);
        writeElement("psns:name",person.getName(),"\t\t\t");
        writer.add(newLineSequence);
        writer.add(tabSequence);
        writer.add(tabSequence);
        writeSimpleTag("psns:person", false);
        writer.add(newLineSequence);
    }

    private void writeElement(String tagName, String value, String controlSeq) throws XMLStreamException {
        XMLEvent controlSeq1 = eventFactory.createCharacters(controlSeq);
        writer.add(controlSeq1);
        writeSimpleTag(tagName, true);
        XMLEvent event = eventFactory.createCharacters(value);
        writer.add(event);
        writeSimpleTag(tagName, false);
    }

    private void writeSimpleTag(String tagName, boolean isOpen) throws XMLStreamException {
        XMLEvent event;
        if (isOpen)
        {
            QName name = QName.valueOf(tagName);
            event = eventFactory.createStartElement(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart());
        } else
            {
            QName name = QName.valueOf(tagName);
            event = eventFactory.createEndElement(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart());
        }
        System.out.println(tagName);
        writer.add(event);
    }



    @Override
    public boolean removePerson(Person person)
    {
        try
        {
            reader = inputFactory.createXMLEventReader(new FileReader(xmlLocation));
            writer = outputFactory.createXMLEventWriter(new FileWriter(new File(xmlLocation)));
            String tagName;
            boolean flag = false;
            while (reader.hasNext())
            {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType())
                {
                    case XMLEvent.START_ELEMENT:
                        if (flag)
                            continue;
                        StartElement element = event.asStartElement();
                        tagName = element.getName().getLocalPart();
                        switch (tagName) {
                            case "person":
                                long currId = Integer.parseInt(element.getAttributeByName(QName.valueOf("id")).getValue());
                                if (currId == person.getId())
                                {
                                    flag = true;
                                    continue;
                                }
                        }
                        break;
                    case XMLEvent.END_ELEMENT:
                        EndElement element1 = event.asEndElement();
                        if (flag) {
                            if ("person".equals(element1.getName().getLocalPart()))
                                flag = false;
                            continue;
                        }
                        break;
                    case XMLEvent.CHARACTERS:
                        if (flag)
                            continue;
                        break;
                }
                writer.add(event);
            }
        } catch (XMLStreamException | IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean updatePerson(Person person1, Person person2)
    {
//        List<Person> persons=getPersons();
//        //persons.get(persons.indexOf(person1))
//        persons.re
//        person2.setGender(person1.getGender());
//        person2.setPreferenceGender(person1.getPreferenceGender());
//        person2.setId(person1.getId());
//        removePerson(person1);
//        addPerson(person2);
//        return false;
        return false;
    }

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

    @Override
    public List<Person> getPersons()
    {
        List<Person> persons = new ArrayList<>();
        Person person = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlLocation));

            while (reader.hasNext())
            {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement())
                {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("person"))
                    {
                        person = new Person();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null)
                            person.setId(Integer.parseInt(idAttr.getValue()));
                    } else if (startElement.getName().getLocalPart().equals("age")) {
                        xmlEvent = reader.nextEvent();
                        System.out.println(Integer.valueOf(xmlEvent.asCharacters().getData()));
                        person.setAge(Integer.valueOf(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equals("height"))
                    {
                        xmlEvent = reader.nextEvent();
                        person.setHeight(Integer.valueOf(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equals("prefgender"))
                    {
                        xmlEvent = reader.nextEvent();
                        person.setPreferenceGender(Gender.valueOf(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("gender")) {
                        xmlEvent = reader.nextEvent();
                        person.setGender(Gender.valueOf(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equals("lprefage")) {
                        xmlEvent = reader.nextEvent();
                        person.setLowPreferenceAge(Integer.valueOf(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equals("hprefage")) {
                        xmlEvent = reader.nextEvent();
                        person.setHighPreferenceAge(Integer.valueOf(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        person.setName(xmlEvent.asCharacters().getData());
                    }
                }
                if (xmlEvent.isEndElement())
                {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("person"))
                        persons.add(person);
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return persons;
    }

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
