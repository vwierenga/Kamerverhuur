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
 * Created by Vincent on 8/29/2016.
 */
@WebServlet("/controller.ShowPersonsServlet")
public class ShowPersonsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        if (session != null && session.getAttribute("login") != null && (Boolean) session.getAttribute("login")) {
            if (action != null) {
                if ("Kamer toevoegen".equals(action)) {
                    getServletContext().getRequestDispatcher("/WEB-INF/addroom.html").forward(request, response);
                } else if ("Kamer zoeken".equals(action)) {
                    getServletContext().getRequestDispatcher("/WEB-INF/huurder.html").forward(request, response);
                } else if ("Uitloggen".equals(action)) {
                    session.setAttribute("login", new Boolean(false));  //Set false when logging out.
                    getServletContext().getRequestDispatcher("/login.html").forward(request, response);
                }
            }

            User currentUser = (User) session.getAttribute("currentUser");
            ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("users");

            printUsers(response, users, currentUser.isOwner());
        }
    }

    private void printUsers(HttpServletResponse response, ArrayList<User> users, boolean owner) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head> \n" +
                "<title>Gevonden Kamers</title> \n" +
                "</head> \n" +
                "<body> \n");
        if (users.size() > 0) {
            for(User user : users) {
                out.println("Naam:  " + user.getUsersname() + " <br> \n" +
                        "Eigenaar:  " + user.isOwner() + "<br> \n" );
            }
        } else {
            out.println("<font color = 'chucknorris'>Er zijn geen gebruikers :(</font><br> <br> \n");
        }

        if(owner) {
            out.println("<form action=\"/showPersons\" method=\"GET\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Kamer toevoegen\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Gebruikers\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Uitloggen\"> \n" +
                    "</form> \n");
        } else {
            out.println("<form action=\"/showUsers\" method=\"GET\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Kamer zoeken\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Gebruikers\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Uitloggen\"> \n" +
                    "</form> \n");
        }

        out.println("</body> \n" +
                "<!--created by TeamRetard's code--> \n" +
                "</html> \n");
    }
}
