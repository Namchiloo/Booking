package sample;

import entity.Listing;
import entity.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminListingRequests implements Initializable
{
    @FXML
    private GridPane gridPane;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // getting all pending user requests from database
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        List<Listing> l;

        try
        {
            transaction.begin();

            Query q = entityManager.createQuery("SELECT e FROM Listing e WHERE e.isApproved = 0");
            l = q.getResultList();


            for (Object p : l) {
                System.out.println(p);
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



        // displaying and setting listeners for all users
        for(int i = 0; i < l.size(); i++)
        {
            Label label = new Label(l.get(i).getTitle());
            Button viewButton = new Button("View");
            int finalI = i;
            EventHandler<ActionEvent> viewUser = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event)
                {
                    System.out.println("Treba otvorit Listing");
                    try {
                        //Load second scene
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListingView.fxml"));
                        Parent root = loader.load();

                        //Get controller of scene2
                        CreateListingRequest scene2Controller = loader.getController();
                        //Pass whatever data you want. You can have multiple method calls here
                        scene2Controller.SetFields(l.get(finalI));

                        //Show scene 2 in new window
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Second Window");
                        stage.show();
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            };
            viewButton.setOnAction(viewUser);

            Button approveButton = new Button("Approve");


            Button declineButton = new Button("Decline");

            EventHandler<ActionEvent> approveUser = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event)
                {
                    System.out.println("Treba potvrdit listing");

                    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
                    EntityManager entityManager = entityManagerFactory.createEntityManager();
                    EntityTransaction transaction = entityManager.getTransaction();
                    try
                    {
                        transaction.begin();
                        l.get(finalI).setIsApproved(1);
                        entityManager.merge(l.get(finalI));
                        transaction.commit();
                    }
                    finally {
                        if (transaction.isActive()) {
                            transaction.rollback();
                        }
                        entityManager.close();
                        entityManagerFactory.close();
                    }
                    viewButton.setOnAction(null);
                    viewButton.setVisible(false);
                    declineButton.setOnAction(null);
                    declineButton.setVisible(false);
                    approveButton.setVisible(false);
                    label.setVisible(false);
                }
            };
            approveButton.setOnAction(approveUser);

            EventHandler<ActionEvent> declineUser = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event)
                {
                    System.out.println("Treba odbit listing");

                    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
                    EntityManager entityManager = entityManagerFactory.createEntityManager();
                    EntityTransaction transaction = entityManager.getTransaction();
                    try
                    {
                        transaction.begin();
                        Listing u = entityManager.find(Listing.class,l.get(finalI).getIdListing());
                        entityManager.remove(u);
                        transaction.commit();
                    }
                    finally {
                        if (transaction.isActive()) {
                            transaction.rollback();
                        }
                        entityManager.close();
                        entityManagerFactory.close();
                    }
                    viewButton.setOnAction(null);
                    viewButton.setVisible(false);
                    declineButton.setOnAction(null);
                    declineButton.setVisible(false);
                    approveButton.setVisible(false);
                    label.setVisible(false);
                }
            };
            declineButton.setOnAction(declineUser);

            gridPane.addRow(i, label, viewButton, approveButton, declineButton);
            gridPane.applyCss();
        }
    }

    public void BackButtonPressed(ActionEvent event) throws Exception
    {
            Parent logInParent = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
    }
}
