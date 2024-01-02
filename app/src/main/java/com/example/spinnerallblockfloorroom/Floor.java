package com.example.spinnerallblockfloorroom;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    String floorName;
    List<Room> rooms;

    public Floor(String floorName) {
        this.floorName = floorName;
        this.rooms = new ArrayList<>();
    }

    public String getFloorName() {
        return floorName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
