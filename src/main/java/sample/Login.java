package sample;

import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.*;
import java.util.List;

public class Login
{

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label errorMessage;

    private boolean loginSuccessful = false;
    private int userType = 0;

    private List<User> l;

    private void LoginLandlord()
    {
        if(username.getText().length() == 0)
        {
            errorMessage.setText("Username is required.");
            return;
        }

        if(password.getText().length() == 0)
        {
            errorMessage.setText("Password is required.");
            return;
        }


        boolean userExists = false;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();



        try
        {
            transaction.begin();

            Query q = entityManager.createQuery("SELECT e FROM User e WHERE e.username = :UN");
            q.setParameter("UN", username.getText());
            l = q.getResultList();


            for (Object p : l) {
                System.out.println(p);
            }

            if(l.isEmpty())
            {
                System.out.println("User with username " + username.getText() + " does not exist in the database");
                userExists = false;
                errorMessage.setText("User with username " + username.getText() + " does not exist in the database");
            }
            else
            {
                System.out.println("User with username " + username.getText() + " exists in the database");
                userExists = true;
                userType = l.get(0).getType();
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

        if (userExists) // ako postoji takav user
        {
            loginSuccessful = true;
            Buffer.bufferUser = l.get(0);
            System.out.println("User logged in:");
        }
        else
        {
            loginSuccessful = false;
            System.out.println("User does not exist in the database!");
        }

        if(!l.isEmpty())
        {
            if(!(l.get(0).getPassword().equals(password.getText())))
            {
                System.out.println("Incorrect password");
                errorMessage.setText("Incorrect password");
                loginSuccessful = false;
            }
            else if(l.get(0).getIsApproved() == 0)
            {
                System.out.println("User not approved by admin");
                errorMessage.setText("User not approved by admin");
                loginSuccessful = false;
            }
        }
    }

    public void LoginButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        LoginLandlord();

        String nextScene = "";

        if (loginSuccessful)
        {
            switch(userType)
            {
                case 1:
                {
                    Buffer.adminLoggedInFlag = true;
                    nextScene = "AdminHome.fxml";
                    break;
                }
                case 2:
                {
                    Buffer.adminLoggedInFlag = false;
                    nextScene = "LandlordHome.fxml";
                    break;
                }
                case 3:
                {
                    Buffer.adminLoggedInFlag = false;
                    Buffer.userLoggedInFlag = true;
                    nextScene = "UserHome.fxml";
                    break;
                }
            }
            Parent logInParent = FXMLLoader.load(getClass().getResource(nextScene));

            Buffer.bufferUser = l.get(0);

            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();


        }
    }

    public void BackButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        Parent logInParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }
}
