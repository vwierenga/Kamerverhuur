package model;

/**
 * Created by Vincent on 8/30/2016.
 */
public class User {
    private String username;
    private String password;
    private boolean owner; //if owner is false then the user is an tenant.

    public User(String username, String password, boolean owner) {
        this.username = username;
        this.password = password;
        this.owner = owner;
    }

    public String getUsersname() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isOwner() {
        return owner;
    }
}

