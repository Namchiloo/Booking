package sample;

import entity.Reservation;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class BookListing implements Initializable
{
    @FXML
    private Label listingTitle;
    @FXML
    private DatePicker checkIn;
    @FXML
    private DatePicker checkOut;
    @FXML
    private Label totalPrice;
    @FXML
    private Label yourPrice;

    @FXML
    private Label errorMessage;

    @FXML
    private Button bookButton;
    @FXML
    private Button payButton;

    private boolean bookingSuccessful = false;

    public void CreateNewReservation()
    {
        Reservation reservation = new Reservation();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        /*System.out.println("Listing id: " + Buffer.bufferListing.getIdListing());
        System.out.println("User id: " + Buffer.bufferUser.getIdUser());
        LocalDate CI = checkIn.getValue();
        Timestamp CITS = Timestamp.valueOf(String.valueOf(CI) + " 00:00:00");
        System.out.println("CHeck in timestamp: " + CITS);
        LocalDate CO = checkOut.getValue();
        Timestamp COTS = Timestamp.valueOf(String.valueOf(CO) + " 00:00:00");
        System.out.println("CHeck out timestamp: " + COTS);
        System.out.println("Days: " + CO.compareTo(CI));*/

        try
        {
            transaction.begin();

            List<Integer> l = entityManager.createQuery(
                    "SELECT u.idReservation FROM Reservation u")
                    .getResultList();

            for (int i = 0; i < l.size(); i++)
            {
                System.out.println(l.get(i));
            }

            Random rand = new Random();
            int upperbound = 500;

            int ID = 0;
            boolean IDexists = true;


            // generating unique ID for the new user
            while(IDexists)
            {
                ID = rand.nextInt(upperbound);
                System.out.println(ID);
                for (int i = 0; i < l.size(); i++)
                {
                    if(ID == l.get(i))
                    {
                        IDexists = true;
                        break;
                    }
                }
                IDexists = false;
            }




            reservation.setIdReservation(ID);
            reservation.setListingByIdListing(Buffer.bufferListing);
            reservation.setUserByIdUser(Buffer.bufferUser);
            LocalDate CI = checkIn.getValue();
            Timestamp CITS = Timestamp.valueOf(String.valueOf(CI) + " 00:00:00");



            LocalDate CO = checkOut.getValue();
            Timestamp COTS = Timestamp.valueOf(String.valueOf(CO) + " 00:00:00");

            LocalDate today = LocalDate.now();

            System.out.println(CI.compareTo(today) + " " + CO.compareTo(CI));

            if(CI.compareTo(today) < 1 || CO.compareTo(CI) < 1)
            {
                errorMessage.setText("Invalid date values");
                return;
            }
            else
            {
                errorMessage.setText("");
            }
            reservation.setCheckIn(CITS);
            reservation.setCheckOut(COTS);
            reservation.setPrice(Buffer.bufferListing.getPricePerNight()*CO.compareTo(CI));
            reservation.setIsPaid((byte)0);
            reservation.setRating(0);
            reservation.setReservationcol1("null");

            bookingSuccessful = true;
            entityManager.persist(reservation);
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

    public void BookButtonPressed(ActionEvent event) throws Exception
    {
        CreateNewReservation();
        if(bookingSuccessful)
        {
            System.out.println("===============================");
            Parent logInParent = FXMLLoader.load(getClass().getResource("BookingSuccessful.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
    }

    public void CalculatePrice()
    {
        LocalDate CI = checkIn.getValue();
        LocalDate CO = checkOut.getValue();
        totalPrice.setText(String.valueOf(Buffer.bufferListing.getPricePerNight()*CO.compareTo(CI)));
        yourPrice.setText(totalPrice.getText()); // ovo popravit, uracunat popust
    }

    public void ViewBooking()
    {
        listingTitle.setText(Buffer.bufferReservation.getListingByIdListing().getTitle());
        checkIn.setValue((Buffer.bufferReservation.getCheckIn()).toLocalDateTime().toLocalDate());
        checkOut.setValue((Buffer.bufferReservation.getCheckOut()).toLocalDateTime().toLocalDate());
        totalPrice.setText(String.valueOf(Buffer.bufferReservation.getPrice()));
        yourPrice.setText(String.valueOf(Buffer.bufferReservation.getPrice() * Buffer.bufferReservation.getListingByIdListing().getDiscount()/100));
        bookButton.setVisible(false);
        if(payButton != null)
            payButton.setVisible(false);
    }

    public void PayBooking()
    {
        listingTitle.setText(Buffer.bufferReservation.getListingByIdListing().getTitle());
        checkIn.setValue((Buffer.bufferReservation.getCheckIn()).toLocalDateTime().toLocalDate());
        checkOut.setValue((Buffer.bufferReservation.getCheckOut()).toLocalDateTime().toLocalDate());
        totalPrice.setText(String.valueOf(Buffer.bufferReservation.getPrice()));
        yourPrice.setText(String.valueOf(Buffer.bufferReservation.getPrice() * Buffer.bufferReservation.getListingByIdListing().getDiscount()/100));
        bookButton.setVisible(false);
        if(payButton != null)
        payButton.setVisible(true);
    }

    public void PayButtonPressed()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        User user = Buffer.bufferReservation.getUserByIdUser();
        try
        {
            if(user.getBalance() > Buffer.bufferReservation.getPrice())
            {
                user.setBalance(user.getBalance()-Buffer.bufferReservation.getPrice());
                errorMessage.setText("Payment success");
            }
            else
            {
                errorMessage.setText("Insufficient founds");

            }
            entityManager.persist(user);
            transaction.commit();
            }
            finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                entityManager.close();
                entityManagerFactory.close();
        }

        payButton.setVisible(false);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listingTitle.setText(Buffer.bufferListing.getTitle());
        bookButton.setVisible(true);
    }
}
