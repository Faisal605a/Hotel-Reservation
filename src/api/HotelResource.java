package api;

import model.Coustomer;
import model.Reservation;
import model.IRoom;
import model.Room;
import service.ReservationService;

import java.util.*;

public class HotelResource {

    public static Map<String, Coustomer> coustmers = new HashMap<String, Coustomer>();
    public static Map<String, Room> rooms = new HashMap<String, Room>();
    public static List<Reservation> Reservations = new LinkedList<Reservation>();
    private static ReservationService reservationService ;

    public HotelResource() {
    }

    public HotelResource(ReservationService reservationService) {
        this.reservationService = ReservationService.getInstance();
    }

    public Coustomer getCoustomer(String email) {
        return coustmers.get(email);
    }

    public void createCoustomer(String email,String firstName, String lastName) {
        coustmers.put(email,new Coustomer(firstName,lastName,email));
    }

    public IRoom getRoom(String roomId){
        return rooms.get(roomId);
    }

    public Reservation bookARoom(String coustomerEmail, Room room, Date chackInDate, Date checkOutDate) {
        return reservationService.reserveRoom(coustmers.get(coustomerEmail),room,chackInDate,checkOutDate);
    }

    public Collection<Reservation> getCoustomerReservations(String coustomerEmail){
        return reservationService.getCoustomerReservations(coustmers.get(coustomerEmail));
    }

    public Collection<Room> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRoom(checkInDate,checkOutDate);
    }
}



