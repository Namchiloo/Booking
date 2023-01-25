package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Reservation {
    private int idReservation;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private double price;
    private int rating;
    private byte isPaid;
    private String reservationcol1;
    private Listing listingByIdListing;
    private User userByIdUser;

    @Id
    @Column(name = "idReservation")
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Basic
    @Column(name = "CheckIn")
    public Timestamp getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    @Basic
    @Column(name = "CheckOut")
    public Timestamp getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    @Basic
    @Column(name = "Price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "IsPaid")
    public byte getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(byte isPaid) {
        this.isPaid = isPaid;
    }

    @Basic
    @Column(name = "Reservationcol1")
    public String getReservationcol1() {
        return reservationcol1;
    }

    public void setReservationcol1(String reservationcol1) {
        this.reservationcol1 = reservationcol1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (idReservation != that.idReservation) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (rating != that.rating) return false;
        if (isPaid != that.isPaid) return false;
        if (checkIn != null ? !checkIn.equals(that.checkIn) : that.checkIn != null) return false;
        if (checkOut != null ? !checkOut.equals(that.checkOut) : that.checkOut != null) return false;
        if (reservationcol1 != null ? !reservationcol1.equals(that.reservationcol1) : that.reservationcol1 != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idReservation;
        result = 31 * result + (checkIn != null ? checkIn.hashCode() : 0);
        result = 31 * result + (checkOut != null ? checkOut.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + rating;
        result = 31 * result + (int) isPaid;
        result = 31 * result + (reservationcol1 != null ? reservationcol1.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idListing", referencedColumnName = "idListing", nullable = false)
    public Listing getListingByIdListing() {
        return listingByIdListing;
    }

    public void setListingByIdListing(Listing listingByIdListing) {
        this.listingByIdListing = listingByIdListing;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}
