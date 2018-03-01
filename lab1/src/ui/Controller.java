package ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class Controller
{
    @FXML
    private ListView listView;

   @FXML public void showInfo(MouseEvent event)
    {
        System.out.println("sadasdasd");
        System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
    }
}
