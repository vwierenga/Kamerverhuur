package model;

import java.util.ArrayList;

/**
 * Created by Vincent on 8/30/2016.
 */
public class RoomModel {
    private static RoomModel ourInstance = new RoomModel();
    private ArrayList<Room> rooms;

    public static RoomModel getInstance() {
        return ourInstance;
    }

    private RoomModel() {
        rooms = new ArrayList<>();
        createTestRooms();
    }

    public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    private void createTestRooms() {
        rooms.add(new Room());
        //rooms.add(new User("owner", "owner", false));
    }
}
