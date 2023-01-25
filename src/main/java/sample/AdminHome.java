package sample;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminHome implements Initializable
{
    @FXML
    private Label numberOfUserRequests;
    @FXML
    private Label numberOfListingRequests;
    @FXML
    private Button logout;
    @FXML
    private Button viewUserRequests;
    @FXML
    private Button viewListingRequests;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        List<Object> l;

        try
        {
            transaction.begin();

            Query q = entityManager.createQuery("SELECT COUNT(e) FROM User e WHERE e.isApproved = 0");

            l = q.getResultList();

            for (Object p : l) {
                System.out.println(p);
            }

            if(l.isEmpty())
            {
                numberOfUserRequests.setText("0");
            }
            else
            {
                numberOfUserRequests.setText(l.get(0).toString());
            }

            q = entityManager.createQuery("SELECT COUNT(e) FROM Listing e WHERE e.isApproved = 0");

            l = q.getResultList();

            for (Object p : l) {
                System.out.println(p);
            }

            if(l.isEmpty())
            {
                numberOfListingRequests.setText("0");
            }
            else
            {
                numberOfListingRequests.setText(l.get(0).toString());
            }

            transaction.commit();
        }
        finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        System.out.println("Pozvalo ga");

    }

    public void Logout(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void ViewUserRequests(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("AdminUserRequests.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void ViewListingRequests(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("AdminListingRequests.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }
}
