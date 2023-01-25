package sample;

import entity.Listing;
import entity.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private HBox listing1;
    @FXML
    private HBox listing2;
    @FXML
    private HBox listing3;
    @FXML
    private HBox listing4;
    @FXML
    private HBox listing5;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button registerButton;

    @FXML
    private ImageView imageView1;
    @FXML
    private Label title1;
    @FXML
    private Label details1;
    @FXML
    private Label grade1;
    @FXML
    private Button view1;

    @FXML
    private ImageView imageView2;
    @FXML
    private Label title2;
    @FXML
    private Label details2;
    @FXML
    private Label grade2;
    @FXML
    private Button view2;

    @FXML
    private ImageView imageView3;
    @FXML
    private Label title3;
    @FXML
    private Label details3;
    @FXML
    private Label grade3;
    @FXML
    private Button view3;

    @FXML
    private ImageView imageView4;
    @FXML
    private Label title4;
    @FXML
    private Label details4;
    @FXML
    private Label grade4;
    @FXML
    private Button view4;

    @FXML
    private ImageView imageView5;
    @FXML
    private Label title5;
    @FXML
    private Label details5;
    @FXML
    private Label grade5;
    @FXML
    private Button view5;

    @FXML
            private Label username;
    @FXML
            private Button profileButton;
    @FXML
            private Button loginButton;

    @FXML
            private Label hello;

    List<Listing> l;
    int timesClicked = 0;

    public void LoadUserHome(ActionEvent event) throws Exception
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try
        {
            transaction.begin();

            List<User> li;

            Query q = entityManager.createQuery("SELECT e FROM User e WHERE e.username = :UN");
            q.setParameter("UN", Buffer.bufferUser.getUsername());
            li = q.getResultList();


            for (Object p : li) {
                System.out.println(p);
            }

            if(li.isEmpty())
            {
                System.out.println("User with username " + username.getText() + " does not exist in the database");
                return;
            }
            else
            {
                System.out.println("User with username " + username.getText() + " exists in the database");
                Buffer.bufferUser = li.get(0);
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

        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("UserHome.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void OpenRegisterScene(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("ChooseRegister.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void OpenLoginScene(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void OpenSearchScene(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void SetListingsInvisible()
    {
        listing1.setVisible(false);
        listing2.setVisible(false);
        listing3.setVisible(false);
        listing4.setVisible(false);
        listing5.setVisible(false);
    }

    private void GetAllListings()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try
        {
            transaction.begin();

            Query q = entityManager.createQuery("SELECT e FROM Listing e WHERE e.isApproved = 1");
            l = q.getResultList();


            for (Object p : l) {
                System.out.println(p);
            }

            if(l.size() < 5)
            {
                nextButton.setVisible(false);
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
    }


    private void ShowNextFiveListings(int startIndex)
    {
        if(startIndex == 0)
        {
            backButton.setVisible(false);
        }

        int upperBound = l.size() < 5 ? l.size() : 5;
        for(int i = 0; i < upperBound; i++)
        {
            if(i+startIndex < l.size())
            {
                Listing currentListing = l.get(i+startIndex);
                System.out.println("sample/"+(currentListing.getImage()).substring(1));
                Image image = new Image("sample/"+(currentListing.getImage()).substring(1));
                EventHandler<ActionEvent> viewListing = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event)
                    {
                        System.out.println("Treba otvorit Listing");
                        try {
                            Buffer.bufferListing = currentListing;
                            //Load second scene
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListingView.fxml"));
                            Parent root = loader.load();

                            //Get controller of scene2
                            CreateListingRequest scene2Controller = loader.getController();
                            //Pass whatever data you want. You can have multiple method calls here
                            scene2Controller.SetFields(currentListing);

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
                switch (i)
                {

                    case 0:
                    {
                        imageView1.setImage(image);
                        title1.setText(currentListing.getTitle());
                        details1.setText(currentListing.getDetailedDescription());
                        view1.setOnAction(viewListing);
                        listing1.setVisible(true);
                        break;
                    }
                    case 1:
                    {
                        imageView2.setImage(image);
                        title2.setText(currentListing.getTitle());
                        details2.setText(currentListing.getDetailedDescription());
                        view2.setOnAction(viewListing);
                        listing2.setVisible(true);
                        break;
                    }
                    case 2:
                    {
                        imageView3.setImage(image);
                        title3.setText(currentListing.getTitle());
                        details3.setText(currentListing.getDetailedDescription());
                        view3.setOnAction(viewListing);
                        listing3.setVisible(true);
                        break;
                    }
                    case 3:
                    {
                        imageView4.setImage(image);
                        title4.setText(currentListing.getTitle());
                        details4.setText(currentListing.getDetailedDescription());
                        view4.setOnAction(viewListing);
                        listing4.setVisible(true);
                        break;
                    }
                    case 4:
                    {
                        imageView5.setImage(image);
                        title5.setText(currentListing.getTitle());
                        details5.setText(currentListing.getDetailedDescription());
                        view5.setOnAction(viewListing);
                        listing5.setVisible(true);
                        break;
                    }
                }
            }
            else
            {
                nextButton.setVisible(false);
                break;
            }
        }
    }

    public void NextPageClicked()
    {
        listing1.setVisible(false);
        listing2.setVisible(false);
        listing3.setVisible(false);
        listing4.setVisible(false);
        listing5.setVisible(false);
        backButton.setVisible(true);
        timesClicked++;
        ShowNextFiveListings(timesClicked*5);
    }

    public void PreviousPageClicked()
    {
        listing1.setVisible(false);
        listing2.setVisible(false);
        listing3.setVisible(false);
        listing4.setVisible(false);
        listing5.setVisible(false);
        backButton.setVisible(true);
        timesClicked--;
        nextButton.setVisible(true);
        ShowNextFiveListings(timesClicked*5);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Buffer.userLoggedInFlag)
        {
            username.setText(Buffer.bufferUser.getUsername());
            loginButton.setVisible(false);
            profileButton.setVisible(true);
            registerButton.setVisible(false);
        }
        else
        {
            loginButton.setVisible(true);
            profileButton.setVisible(false);
        }
        backButton.setVisible(false);
        timesClicked = 0;
        SetListingsInvisible();
        if(Buffer.searchFlag)
        {
            l = Buffer.searchListingList;
            Buffer.searchFlag = false;
            if(l.size() < 5)
            {
                nextButton.setVisible(false);
            }
        }
        else
        {
            GetAllListings();
        }
        ShowNextFiveListings(0);
    }
}
