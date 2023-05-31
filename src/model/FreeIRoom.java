package model;

public class FreeIRoom extends Room {

    public FreeIRoom() {
        super("", RoomType.SINGLE,0.0);
    }

    @Override
    public String toString() {
        return "FreeRoom";
    }
}

