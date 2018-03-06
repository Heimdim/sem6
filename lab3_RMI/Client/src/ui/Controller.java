package ui;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import dao.PersonDAOImpl;
import entity.Gender;
import entity.Person;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller
{
    PersonDAOImpl pdi=new PersonDAOImpl();

    @FXML
    private ListView listViewPerson, listViewPartner,listViewPerson1;
    @FXML
    private TextArea infoTextPerson, infoTextPartner;
    @FXML
    private TextField ageField, heightField, lPrefAgeField, hPrefAgeField, nameField;
    @FXML
    private ChoiceBox genderChoice, prefGenderChoice;

    @FXML public void showPersonInfo(MouseEvent event)
    {
        infoTextPerson.clear();
        Person temp=(Person) listViewPerson.getSelectionModel().getSelectedItem();
        infoTextPerson.appendText(temp.toString().replace(',','\n'));
        List<Person>list=pdi.getSuitablePersons(temp);
        listViewPartner.setItems(FXCollections.observableArrayList(list));
    }

    @FXML public void showPartnerInfo(MouseEvent event)
    {
        infoTextPartner.clear();
        Person temp=(Person) listViewPartner.getSelectionModel().getSelectedItem();
        infoTextPartner.appendText(temp.toString().replace(',','\n'));
    }

    @FXML
    public void initialize()
    {
        loadPersonsFromDB();
        genderChoice.setItems(FXCollections.observableArrayList(Gender.values()));
        prefGenderChoice.setItems(FXCollections.observableArrayList(Gender.values()));
    }

    private void loadPersonsFromDB()
    {
        List<Person> list=pdi.getPersons();
        listViewPerson.setItems(FXCollections.observableArrayList(list));
        listViewPerson1.setItems(FXCollections.observableArrayList(list));
    }


    public void addPerson(ActionEvent actionEvent)
    {
        try
        {
             Gender  gender=(Gender)genderChoice.getValue(), prefGender=(Gender)prefGenderChoice.getValue();
             Person  temp=new Person(Integer.valueOf(ageField.getText()),Integer.valueOf(heightField.getText()),
                     prefGender,gender,Integer.valueOf(lPrefAgeField.getText()),Integer.valueOf(hPrefAgeField.getText()),
                     nameField.getText());

             pdi.addPerson(temp);
             loadPersonsFromDB();
        }
        catch (NumberFormatException ex)
        {
          showError("Ooops, you just forget to fill some fields");
        }
        clearControls();
    }

    public void removePerson(ActionEvent actionEvent)
    {
        try
        {
            pdi.removePerson((Person) listViewPerson1.getSelectionModel().getSelectedItem());
            loadPersonsFromDB();
            clearControls();
        }
        catch (NullPointerException ex)
        {showError("Ooops, you just forget to choose person to remove");}
    }

    public void updatePerson(ActionEvent actionEvent)
    {
        Person curPerson=(Person)listViewPerson1.getSelectionModel().getSelectedItem();
        if(curPerson != null)
        {
            Gender gender = (genderChoice.getValue() == null) ? curPerson.getGender() : (Gender) genderChoice.getValue(),
                   prefGender = (prefGenderChoice.getValue() == null) ? curPerson.getPreferenceGender() : (Gender) prefGenderChoice.getValue();

            int age = (ageField.getText().equals("")) ? curPerson.getAge() : Integer.valueOf(ageField.getText()),
                height = (heightField.getText().equals("")) ? curPerson.getHeight() : Integer.valueOf(heightField.getText()),
                lPrefAge = (lPrefAgeField.getText().equals("")) ? curPerson.getLowPreferenceAge() : Integer.valueOf(lPrefAgeField.getText()),
                hPrefAge = (hPrefAgeField.getText().equals("")) ? curPerson.getHighPreferenceAge() : Integer.valueOf(hPrefAgeField.getText());
            String name = (nameField.getText().equals("")) ? curPerson.getName() : nameField.getText();

            Person updatePerson = new Person(age, height, prefGender, gender, lPrefAge, hPrefAge, name);

            if (!curPerson.equals(updatePerson))
            {
                pdi.updatePerson(curPerson, updatePerson);
                loadPersonsFromDB();
                clearControls();
            } else
                showError("Ooops, you just forget to make changes");
        }
        else showError("Ooops, you just forget to choose person to update");



    }

    private void clearControls()
    {
        ageField.clear();
        heightField.clear();
        lPrefAgeField.clear();
        hPrefAgeField.clear();
        nameField.clear();
        genderChoice.setValue(null);
        prefGenderChoice.setValue(null);
    }

    private void showError(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText(msg);

        alert.showAndWait();
    }
}
