package sample;
/*import entity.Listing;
import entity.User;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try
        {
            transaction.begin();

            User newUser = new User();
            newUser.setIdUser(1);
            newUser.setName("Edin");
            newUser.setLastName("Mustafic");
            newUser.setPhoneNumber("061817940");
            newUser.setUsername("EdoBog");
            newUser.setPassword("zazaPachulia06061944");
            newUser.setIsApproved(1);
            newUser.setBalance(9999.9);
            newUser.setType(1);
            entityManager.persist(newUser);
            transaction.commit();
        }
        finally
        {
            if(transaction.isActive())
            {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}*/

// komentar


import entity.Listing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Listing l1 = new Listing();
    Listing l = new Listing();

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        /*primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 880, 690));
        primaryStage.show();*/
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    private static void search()
    {

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
