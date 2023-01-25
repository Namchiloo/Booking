package sample;

import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class LandlordRegistrationRequest {
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label username;
    @FXML
    private Label type;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label balance;

    public void BackButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void SetFields(User user)
    {
        System.out.println("SetFields called with user " + user);
        firstName.setText(user.getName());
        lastName.setText(user.getLastName());
        username.setText(user.getUsername());
        type.setText(user.getType() == 2 ? "Landlord" : "User");
        phoneNumber.setText(user.getPhoneNumber());
        balance.setText(((Double) user.getBalance()).toString());
    }

}
