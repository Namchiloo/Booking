package sample;

import entity.Listing;
import entity.Reservation;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class Buffer
{
    static User bufferUser;
    static Listing bufferListing;
    static ArrayList<Listing> searchListingList;
    static boolean searchFlag;
    static boolean userLoggedInFlag;
    static boolean adminLoggedInFlag;
    static Reservation bufferReservation;
}
