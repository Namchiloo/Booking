package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Listing {
    private int idListing;
    private int isApproved;
    private int type;
    private String title;
    private String country;
    private String address;
    private int surfaceArea;
    private int numberOfRooms;
    private int numberOfBeds;
    private String detailedDescription;
    private String image;
    private double pricePerNight;
    private int discount;
    private byte ac;
    private byte wiFi;
    private byte parking;
    private byte kitchen;
    private byte tv;
    private byte garden;
    private byte balcony;
    private byte smoking;
    private byte pets;
    private byte onlinePayment;
    private User userByIdUser;
    private Collection<Reservation> reservationsByIdListing;

    @Id
    @Column(name = "idListing")
    public int getIdListing() {
        return idListing;
    }

    public void setIdListing(int idListing) {
        this.idListing = idListing;
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
    @Column(name = "Type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "SurfaceArea")
    public int getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(int surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    @Basic
    @Column(name = "NumberOfRooms")
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Basic
    @Column(name = "NumberOfBeds")
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    @Basic
    @Column(name = "DetailedDescription")
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    @Basic
    @Column(name = "Image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "PricePerNight")
    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Basic
    @Column(name = "Discount")
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "AC")
    public byte getAc() {
        return ac;
    }

    public void setAc(byte ac) {
        this.ac = ac;
    }

    @Basic
    @Column(name = "WiFi")
    public byte getWiFi() {
        return wiFi;
    }

    public void setWiFi(byte wiFi) {
        this.wiFi = wiFi;
    }

    @Basic
    @Column(name = "Parking")
    public byte getParking() {
        return parking;
    }

    public void setParking(byte parking) {
        this.parking = parking;
    }

    @Basic
    @Column(name = "Kitchen")
    public byte getKitchen() {
        return kitchen;
    }

    public void setKitchen(byte kitchen) {
        this.kitchen = kitchen;
    }

    @Basic
    @Column(name = "TV")
    public byte getTv() {
        return tv;
    }

    public void setTv(byte tv) {
        this.tv = tv;
    }

    @Basic
    @Column(name = "Garden")
    public byte getGarden() {
        return garden;
    }

    public void setGarden(byte garden) {
        this.garden = garden;
    }

    @Basic
    @Column(name = "Balcony")
    public byte getBalcony() {
        return balcony;
    }

    public void setBalcony(byte balcony) {
        this.balcony = balcony;
    }

    @Basic
    @Column(name = "Smoking")
    public byte getSmoking() {
        return smoking;
    }

    public void setSmoking(byte smoking) {
        this.smoking = smoking;
    }

    @Basic
    @Column(name = "Pets")
    public byte getPets() {
        return pets;
    }

    public void setPets(byte pets) {
        this.pets = pets;
    }

    @Basic
    @Column(name = "OnlinePayment")
    public byte getOnlinePayment() {
        return onlinePayment;
    }

    public void setOnlinePayment(byte onlinePayment) {
        this.onlinePayment = onlinePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Listing listing = (Listing) o;

        if (idListing != listing.idListing) return false;
        if (isApproved != listing.isApproved) return false;
        if (type != listing.type) return false;
        if (surfaceArea != listing.surfaceArea) return false;
        if (numberOfRooms != listing.numberOfRooms) return false;
        if (numberOfBeds != listing.numberOfBeds) return false;
        if (Double.compare(listing.pricePerNight, pricePerNight) != 0) return false;
        if (discount != listing.discount) return false;
        if (ac != listing.ac) return false;
        if (wiFi != listing.wiFi) return false;
        if (parking != listing.parking) return false;
        if (kitchen != listing.kitchen) return false;
        if (tv != listing.tv) return false;
        if (garden != listing.garden) return false;
        if (balcony != listing.balcony) return false;
        if (smoking != listing.smoking) return false;
        if (pets != listing.pets) return false;
        if (onlinePayment != listing.onlinePayment) return false;
        if (title != null ? !title.equals(listing.title) : listing.title != null) return false;
        if (country != null ? !country.equals(listing.country) : listing.country != null) return false;
        if (address != null ? !address.equals(listing.address) : listing.address != null) return false;
        if (detailedDescription != null ? !detailedDescription.equals(listing.detailedDescription) : listing.detailedDescription != null)
            return false;
        if (image != null ? !image.equals(listing.image) : listing.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idListing;
        result = 31 * result + isApproved;
        result = 31 * result + type;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + surfaceArea;
        result = 31 * result + numberOfRooms;
        result = 31 * result + numberOfBeds;
        result = 31 * result + (detailedDescription != null ? detailedDescription.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        temp = Double.doubleToLongBits(pricePerNight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + discount;
        result = 31 * result + (int) ac;
        result = 31 * result + (int) wiFi;
        result = 31 * result + (int) parking;
        result = 31 * result + (int) kitchen;
        result = 31 * result + (int) tv;
        result = 31 * result + (int) garden;
        result = 31 * result + (int) balcony;
        result = 31 * result + (int) smoking;
        result = 31 * result + (int) pets;
        result = 31 * result + (int) onlinePayment;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @OneToMany(mappedBy = "listingByIdListing")
    public Collection<Reservation> getReservationsByIdListing() {
        return reservationsByIdListing;
    }

    public void setReservationsByIdListing(Collection<Reservation> reservationsByIdListing) {
        this.reservationsByIdListing = reservationsByIdListing;
    }
}
