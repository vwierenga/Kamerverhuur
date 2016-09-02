package model;

/**
 * Created by Vincent on 8/30/2016.
 */
public class User {
    private String username;
    private String password;
    private boolean tenant; //if tenant is false then the user is an owner.

    public User(String username, String password, boolean tenant) {
        this.username = username;
        this.password = password;
        this.tenant = tenant;
    }

    public String getUsersname() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTenant() {
        return tenant;
    }
}

