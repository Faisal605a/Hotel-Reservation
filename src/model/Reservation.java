package model;

import java.util.Date;
import java.util.Objects;

public class Reservation implements Comparable<Reservation> {
    private Coustomer coustomer;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return coustomer.equals(that.coustomer) && room.equals(that.room) && checkInDate.equals(that.checkInDate) && checkOutDate.equals(that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coustomer, room, checkInDate, checkOutDate);
    }

    @Override
    public int compareTo(Reservation o) {
    if(o.checkInDate.after(checkOutDate) && o.checkOutDate.before(checkInDate))
        return 1;
    else
        return 0;
    }

    public Reservation(Coustomer coustomer, Room room, Date checkInDate, Date checkOutDate) {
        this.coustomer = coustomer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation" +
                "Customer :" + coustomer +
                ", Room: " + room +
                ", CheckIn Date: " + checkInDate +
                ", CheckOut Date: " + checkOutDate ;
    }

    public final Coustomer getCoustomer() {
        return coustomer;
    }

    public final Room getRoom() {
        return room;
    }

    public final Date getCheckInDate() {
        return checkInDate;
    }

    public final Date getCheckOutDate() {
        return checkOutDate;
    }
}
