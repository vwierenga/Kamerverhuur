package controller;

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
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(

                "<head> \n" +
                "<title>Tetten</title> \n" +
                "</head> \n" +
                "<body> \n" +
                "Uw gebruikersnaam en wachtwoord is <font color = 'chucknorris'>correct</font>! Victory achieved<br> <br> \n" +
                "<a href='login.html'>login</a> \n" +
                "</body> \n" +
                "<!--created by TeamRetard's code--> \n" +
                "</html> \n");
                //out.write("INGELOGD!!!!!!!!!!!");

                HttpSession session = request.getSession();
                session.setAttribute("login", new Boolean(true));  //Set false when logging out.

                //response.sendRedirect("");


                //HttpSession session = request.getSession(false); maakt geen session aan
            } else {
                System.out.println("Fout ww.");
                response.sendRedirect("fouteinlog.html");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean checkPassword(User user, String password) {
        return (user.getPassword().equals(password));
    }

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
