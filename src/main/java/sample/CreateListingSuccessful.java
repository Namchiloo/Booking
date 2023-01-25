package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateListingSuccessful
{
    public void OkButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
