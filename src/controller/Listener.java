package controller;

import model.Room;
import model.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

/**
 * Created by Vincent on 9/5/2016.
 */
@WebListener()
public class Listener implements ServletContextListener {


    public Listener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("tenant", "tenant", false));
        users.add(new User("owner", "owner", true));
        users.add(new User("admin", "admin", true));
        users.add(new User("user", "user", false));

        servletContextEvent.getServletContext().setAttribute("users", users);

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(15, 320, "randomStreet", users.get(1)));

        servletContextEvent.getServletContext().setAttribute("rooms", rooms);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
