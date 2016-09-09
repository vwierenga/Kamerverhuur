package model;

/**
 * Created by Vincent on 9/2/2016.
 */
public class Room {
    private int size;
    private double maxPrice;
    private String address;
    private User owner;

    public Room(int size, double maxPrice, String address, User owner) {
        this.size = size;
        this.maxPrice = maxPrice;
        this.address = address;
        this.owner = owner;
    }
}
