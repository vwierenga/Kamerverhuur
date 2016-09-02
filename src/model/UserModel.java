package model;

import java.util.ArrayList;

/**
 * Created by Vincent on 8/30/2016.
 */
public class UserModel {
    private static UserModel ourInstance = new UserModel();
    private ArrayList<User> users;

    public static UserModel getInstance() {
        return ourInstance;
    }

    private UserModel() {
        users = new ArrayList<>();
        createTestUsers();
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    private void createTestUsers() {
        users.add(new User("tenant", "tenant", true));
        users.add(new User("owner", "owner", false));
    }

    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsersname().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }
}
