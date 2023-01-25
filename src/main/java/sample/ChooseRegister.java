package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChooseRegister
{
    @FXML
    private MenuItem Landlord;
    @FXML
    private MenuItem User;
    @FXML
    private Label InfoLabel;

    public void UserMenuItemSelected(javafx.event.ActionEvent event) throws Exception
    {
        InfoLabel.setText("User registration selected");
    }

    public void LandlordMenuItemSelected(javafx.event.ActionEvent event) throws Exception
    {
        InfoLabel.setText("Landlord registration selected");
    }

    public void NextButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        if(InfoLabel.getText() == "User registration selected")
        {
            Parent logInParent = FXMLLoader.load(getClass().getResource("UserRegister.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
        else if(InfoLabel.getText() == "Landlord registration selected")
        {
            Parent logInParent = FXMLLoader.load(getClass().getResource("LandlordRegister.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
        else
        {
            InfoLabel.setText("Please select one of the options above");
        }
    }
}
