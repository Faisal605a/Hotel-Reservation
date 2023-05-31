package service;

import api.HotelResource;
import model.Coustomer;
import model.Reservation;
import model.IRoom;
import model.Room;

import java.util.*;

public class ReservationService {

    private static ReservationService INSTANCE;

    public static ReservationService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ReservationService();
        }

        return INSTANCE;
    }
    private ReservationService() {
    }

    static boolean IsRoomAvailable(){
        return true;
    }
    public Collection<Coustomer> getAllCustomers(){
        return HotelResource.coustmers.values();
    }
    public void addRoom(Room room){
        HotelResource.rooms.put(room.getRoomNumber(),room);

    }

    public static IRoom getRoom(String roomId){
        return HotelResource.rooms.get(roomId);
    }

    public static Reservation reserveRoom(Coustomer coustomer, Room room, Date checkInDate, Date checkOutDate){
       if(HotelResource.Reservations.add(new Reservation(coustomer,room, checkInDate, checkOutDate)))
       return new Reservation(coustomer,room, checkInDate, checkOutDate);
       else
           return null;
    }

    // you should compare date provided with existing date and see if there is room available
    public static Collection<Room> findRoom(Date checkInDate, Date checkOutDate) {

        return searchFoARoom(checkInDate, checkOutDate);
    }

    public static Collection<Reservation> getCoustomerReservations(Coustomer coustomer){
        List<Reservation> coustomerReservations  = new ArrayList<Reservation>();

        for(Reservation reserv : HotelResource.Reservations) {
            // if customer email is the same we get the room
            if (reserv.getCoustomer().getEmail().equals(coustomer.getEmail()))
                coustomerReservations.add(reserv);
        }
        return coustomerReservations;

    }

    public static void printAllReservations(){
        System.out.println("Reservations:");
        for(Reservation reserv : HotelResource.Reservations)
            System.out.println(reserv);
    }
    public static Collection<Room> getAllRooms(){
        return HotelResource.rooms.values();
    }

    private static Collection<Room> searchFoARoom(Date checkInDate, Date checkOutDate){

        List<Room> avaliableRooms = new LinkedList<Room>();
        Boolean isRoomAvailable = true;
        int pointer=0;

        while(pointer < HotelResource.rooms.values().size())
        {
            isRoomAvailable = true;
            Room room = (Room) HotelResource.rooms.values().toArray()[pointer];

            for(Reservation reserv : HotelResource.Reservations) {
                // if checkin after any checkin and before any checkout or checkout after checkin or before checkout then room is not available
                if (room.getRoomNumber() == reserv.getRoom().getRoomNumber()) {
                    if ((checkInDate.compareTo(reserv.getCheckInDate()) == 0 || checkOutDate.compareTo(reserv.getCheckOutDate()) == 0) ||
                            (checkInDate.after(reserv.getCheckInDate()) && checkInDate.before(reserv.getCheckOutDate())) ||
                            (checkOutDate.after(reserv.getCheckInDate()) && checkOutDate.before(reserv.getCheckOutDate())) ||
                            (reserv.getCheckInDate().after(checkInDate) && reserv.getCheckInDate().before(checkOutDate)) ||
                            (reserv.getCheckOutDate().after(checkInDate) && reserv.getCheckOutDate().before(checkOutDate))) {
                        isRoomAvailable = false;

                        break;
                    }


                }
            }
            if(isRoomAvailable)
                avaliableRooms.add((Room) HotelResource.rooms.values().toArray()[pointer]);

            pointer++;
        }

        return avaliableRooms;
    }
}
