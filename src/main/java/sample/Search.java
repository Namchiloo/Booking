package sample;

//import com.sun.jndi.toolkit.dir.SearchFilter;
import entity.Listing;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Search
{
    @FXML
    private TextField country;
    @FXML
    private TextField city;
    @FXML
    private TextField noOfRooms;
    @FXML
    private TextField noOfBeds;
    @FXML
    private CheckBox wiFi;
    @FXML
    private CheckBox parking;
    @FXML
    private TextField pricePerNight;
    @FXML
    private Label errorMessage;

    private boolean allFieldsHaveValue = false;

    private boolean checkNumber(String str)
    {
        boolean retval = false;
        int n = str.length();

        for (int i = 0; i < n; i++)
        {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
            {
                retval = true;
            }
            else
            {
                return false;
            }
        }
        return retval;
    }

    public void CheckSearchParameters()
    {
        System.out.println("Checking search parameters");
        if(country.getText() == "" || city.getText() == "" || noOfRooms.getText() == "" || noOfBeds.getText() == "" || pricePerNight.getText() == "")
        {
            errorMessage.setText("All search fields must be filled");
        }
        else if(!checkNumber(noOfBeds.getText()))
        {
            errorMessage.setText("Invalid beds number.");
        }
        else if(!checkNumber(noOfRooms.getText()))
        {
            errorMessage.setText("Invalid rooms number.");
        }
        else if(!checkNumber(pricePerNight.getText()))
        {
            errorMessage.setText("Invalid price number.");
        }
        else
        {
            allFieldsHaveValue = true;
        }
    }

    public void SearchFunc()
    {
        System.out.println("SearchFunc called");
        CheckSearchParameters();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try
        {
            transaction.begin();

            Query q = entityManager.createQuery("SELECT e FROM Listing e WHERE e.isApproved = 1 AND e.country = :ctry AND e.numberOfBeds >= :nob AND e.numberOfRooms >= :nor AND e.parking = :p AND e.wiFi = :w AND e.pricePerNight <= :ppn");
            q.setParameter("ctry", country.getText());
            q.setParameter("nob", Integer.parseInt(noOfBeds.getText()));
            q.setParameter("nor", Integer.parseInt(noOfRooms.getText()));
            q.setParameter("p", parking.isSelected() ? (byte)1 : (byte)0);
            q.setParameter("w", wiFi.isSelected() ? (byte)1 : (byte)0);
            q.setParameter("ppn", Double.parseDouble(pricePerNight.getText()));
            List<Listing> l = q.getResultList();

            ArrayList<Integer> toSave = new ArrayList<>();

            for(int i = 0; i < l.size(); i++)
            {
                if(city.getText().equals(l.get(i).getAddress().substring(0,city.getText().length())))
                {
                    toSave.add(i);
                }
            }

            System.out.println(toSave.size());

            ArrayList<Listing> l1 = new ArrayList<>();

            for(int j = 0; j < toSave.size(); j++)
            {
                l1.add(l.get(toSave.get(j)));
            }



            Buffer.searchListingList = l1;
            Buffer.searchFlag = true;

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


    public void SearchButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        SearchFunc();

        if(allFieldsHaveValue)
        {
            Parent logInParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
    }
}
