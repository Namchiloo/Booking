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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;

public class UserRegister
{
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label errorMessage;

    boolean registrationSuccessful = false;

    private boolean checkName(String str)
    {
        System.out.println("Checking name");

        if(str.length() == 0)
        {
            errorMessage.setText("First name is required.");
            return false;
        }

        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z'))
            {
                errorMessage.setText("Invalid name.\nName should contain only letters.");
                return false;
            }
        }
        return true;
    }

    private boolean checkLastName(String str)
    {

        if(str.length() == 0)
        {
            errorMessage.setText("Last name is required.");
            return false;
        }

        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z'))
            {
                errorMessage.setText("Invalid last name.\nLast name should contain only letters.");
                return false;
            }
        }
        return true;
    }

    private boolean checkPhoneNumber(String str)
    {
        boolean retval = false;
        int n = str.length();

        if(str == null || str.equals(" "))
        {
            errorMessage.setText("Phone number is required.");
            return false;
        }

        // napravi provjeru unutar baze podataka da li je dati phone number vec unesen. ako jest vrati false
        /*else if(nesto)
        {
            errorMessage.setText("Phone number in use by other landlord");
            return false;
        }*/

        if(n < 9)
        {
            errorMessage.setText("Invalid phone number.\nMinimum phone number length is 9");
            return false;
        }
        for (int i = 0; i < n; i++)
        {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
            {
                retval = true;
            }
            else
            {
                errorMessage.setText("Invalid phone number.\nPhone number should contain only digits");
                return false;
            }
        }
        return retval;
    }

    private boolean checkUsername(String str)
    {
        if(str.length() == 0)
        {
            errorMessage.setText("Username is required.");
            return false;
        }

        if(str.length() > 26)
        {
            errorMessage.setText("Username too long");
            return false;
        }
        else if(str.length() < 4)
        {
            errorMessage.setText("Username too short\nMinimum username length is 4");
            return false;
        }
        // napravi provjeru unutar baze podataka da li je dati username zauzet. ako jest vrati false
        /*else if(nesto)
        {
            errorMessage.setText("Username taken");
            return false;
        }*/

        return true;
    }

    private boolean checkPassword(String str)
    {
        if(str.length() == 0)
        {
            errorMessage.setText("Password is required.");
            return false;
        }

        if(str.length() > 26)
        {
            errorMessage.setText("Password too long");
            return false;
        }
        else if(str.length() < 8)
        {
            errorMessage.setText("Password too short.\nMinimum password length is 8");
            return false;
        }
        return true;
    }

    private void RegisterUser()
    {
        if (checkName(firstName.getText()) && checkLastName(lastName.getText()) && checkPhoneNumber(phoneNumber.getText()) && checkUsername(username.getText()) && checkPassword(password.getText()))
        {
            User newUser = new User();

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();



            try {
                transaction.begin();

                List<Integer> l = entityManager.createQuery(
                        "SELECT u.idUser FROM User u")
                        .getResultList();

                List<String> takenUsernames = entityManager.createQuery(
                        "SELECT u.username FROM User u")
                        .getResultList();

                for (int i = 0; i < l.size(); i++) {
                    System.out.println(l.get(i));
                }

                Random rand = new Random();
                int upperbound = 100;

                int ID = 0;
                boolean IDexists = true;


                // generating unique ID for the new user
                while (IDexists) {
                    ID = rand.nextInt(upperbound);
                    System.out.println(ID);
                    for (int i = 0; i < l.size(); i++) {
                        if (ID == l.get(i)) {
                            IDexists = true;
                            break;
                        }
                    }
                    IDexists = false;
                }

                for (int i = 0; i < takenUsernames.size(); i++)
                {
                    registrationSuccessful = false;
                    System.out.println(takenUsernames.get(i));

                    if(takenUsernames.get(i).equals(username.getText()))
                    {
                        System.out.println("Taken");
                        errorMessage.setText("Username taken");
                        return;
                    }
                }


                newUser.setIdUser(ID);
                newUser.setName(firstName.getText());
                newUser.setLastName(lastName.getText());
                newUser.setPhoneNumber(phoneNumber.getText());
                newUser.setUsername(username.getText());
                newUser.setPassword(password.getText());
                newUser.setIsApproved(0);
                newUser.setBalance(500);
                newUser.setType(3);

                entityManager.persist(newUser);
                transaction.commit();
            }
            finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                entityManager.close();
                entityManagerFactory.close();
            }
            registrationSuccessful = true;
            System.out.println("Registered new tennant: \n" + newUser.toString());

        }
    }

    public void RegisterButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        RegisterUser();

        if (registrationSuccessful)
        {
            Parent logInParent = FXMLLoader.load(getClass().getResource("RegistrationSuccessful.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
    }

}
