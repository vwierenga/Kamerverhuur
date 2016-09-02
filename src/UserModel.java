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

    public void addUser(User gebruiker) {
        users.add(gebruiker);
    }

    private void createTestUsers() {
        users.add(new User("tenant", "tenant", true));
        users.add(new User("owner", "owner", false));
    }
}
