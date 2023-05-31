package api;

import model.Coustomer;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private HotelResource hotelResource ;
    private static CustomerService customerService ;
    private static ReservationService reservationService ;

    public AdminResource() {

    }



    public Coustomer getCoustomer(String email) {
        return hotelResource.getCoustomer(email);
    }

    public void addRoom(List<Room> rooms){
        for(Room room :rooms )
            reservationService.addRoom(room);
    }

    public Collection<Coustomer> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservtion(){
        System.out.println(hotelResource.Reservations);
    }
}
