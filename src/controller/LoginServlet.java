package controller;

import model.Room;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Vincent on 8/31/2016.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = findUser(username);

        if (user == null) {
            System.out.println("User not found");
            response.sendRedirect("fouteinlog.html");
        } else {
            System.out.println(user.getUsersname() + " found");
            if (checkPassword(user, password)) {

                HttpSession session = request.getSession();
                session.setAttribute("login", new Boolean(true));  //Set false when logging out.
                session.setAttribute("currentUser", user); //Saves the current user for later use.

                //Send the user to the right page.
                if(user.isOwner()) {
                    getServletContext().getRequestDispatcher("/showRooms").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/WEB-INF/huurder.html").forward(request, response);
                }
            } else {
                response.sendRedirect("fouteinlog.html");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Check if the password is correct
     * @param user the username of the user who wants to log in.
     * @param password the password of the user who wants to log in.
     * @return true if the password is correct.
     */
    private boolean checkPassword(User user, String password) {
        return (user.getPassword().equals(password));
    }

    /**
     * Checks if the user already exists
     * @param username the username of the user we're looking for.
     * @return a user if the user exists or null if the user doesn't exist.
     */
    private User findUser(String username) {
        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("users");
        for (User user : users) {
            if (user.getUsersname().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }
}
