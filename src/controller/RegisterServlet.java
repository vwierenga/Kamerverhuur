package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Rick on 30-8-2016.
 */
@WebServlet("/controller.RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private ArrayList<User> users;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("gebruikersnaam");

        users = (ArrayList<User>) getServletContext().getAttribute("users");

        //Checks if the username already exists.
        User user = checkIfUserExists(username);
        if (user == null) {
            String password = request.getParameter("wachtwoord");
            String type = request.getParameter("type");

            if (type.equals("owner")) {

                users.add(new User(username, password, true));
                System.out.println(username + " added as owner");
            } else {
                users.add(new User(username, password, false));
                System.out.println(username + " added as tenant");
            }

        } else {
            System.out.println("user already exists");
            System.out.println("git gud faggot");
            System.out.println("n00b");
            System.out.println("pleb");
            //fuckoff
        }
        //Redirects our new user to the login page.
        response.sendRedirect("login.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Function to check if the username is already in use.
     * @param username the username in question.
     * @return A user if the username is in use or null if the username isn't taken yet.
     */
    public User checkIfUserExists(String username) {
        for (User user : users) {
            if (user.getUsersname().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }
}
