package model;

/**
 * Created by Vincent on 9/2/2016.
 */
public class Room {
    private int size;
    private double price;
    private String address;
    private String city;
    private User owner;

    public Room(int size, double price, String address, String city, User owner) {
        this.size = size;
        this.price = price;
        this.address = address;
        this.city = city;
        this.owner = owner;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public User getOwner() {
        return owner;
    }
}
