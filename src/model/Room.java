package model;

import java.util.Objects;

public class Room implements IRoom {

    private final String roomNumber;
    private final RoomType type;
    private final Double price;

    public Room(String roomNumber, RoomType type, Double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    @Override
    public String getRoomNumber() {

        return roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room roomImple = (Room) o;
        return Objects.equals(roomNumber, roomImple.roomNumber) && type == roomImple.type && Objects.equals(price, roomImple.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, type, price);
    }

    @Override
    public String toString() {
        return " Room Number: " + roomNumber +" "+type +" ,bed Room"+ " ,Price: " + price;
    }

    @Override
    public final Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return type;
    }

    @Override
    public boolean isRoomAvailable() {
        return false;
    }
}
