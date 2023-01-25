package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
    private int idUser;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String password;
    private int isApproved;
    private double balance;
    private int type;
    private Collection<Listing> listingsByIdUser;
    private Collection<Reservation> reservationsByIdUser;

    @Id
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "PhoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "IsApproved")
    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    @Basic
    @Column(name = "Balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "Type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != user.idUser) return false;
        if (isApproved != user.isApproved) return false;
        if (Double.compare(user.balance, balance) != 0) return false;
        if (type != user.type) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idUser;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + isApproved;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + type;
        return result;
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<Listing> getListingsByIdUser() {
        return listingsByIdUser;
    }

    public void setListingsByIdUser(Collection<Listing> listingsByIdUser) {
        this.listingsByIdUser = listingsByIdUser;
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<Reservation> getReservationsByIdUser() {
        return reservationsByIdUser;
    }

    public void setReservationsByIdUser(Collection<Reservation> reservationsByIdUser) {
        this.reservationsByIdUser = reservationsByIdUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isApproved=" + isApproved +
                ", balance=" + balance +
                ", type=" + type +
                ", listingsByIdUser=" + listingsByIdUser +
                ", reservationsByIdUser=" + reservationsByIdUser +
                '}';
    }
}
