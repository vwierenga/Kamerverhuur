package controller;

import model.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;

/**
 * Created by Vincent on 9/5/2016.
 */
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("tenant", "tenant", false));
        users.add(new User("owner", "owner", true));
        users.add(new User("admin", "admin", true));
        users.add(new User("user", "user", false));

        servletContextEvent.getServletContext().setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
