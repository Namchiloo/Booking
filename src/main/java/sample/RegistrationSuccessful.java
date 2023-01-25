package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrationSuccessful
{
    public void OkButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        Parent logInParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void OkButtonPressedReservation(javafx.event.ActionEvent event) throws Exception
    {
        Parent logInParent = FXMLLoader.load(getClass().getResource("UserHome.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }
}
