package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vincent on 8/29/2016.
 */
@WebServlet("/controller.ShowPersonsServlet")
public class ShowPersonsServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        //Not used because it does not have the request and response variables
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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

            printUsers(request, response, users, currentUser.isOwner());
        }
    }

    /**
     * Displays the users on screen and creates two cookies
     * @param request HttpServletRequest needed for cookies
     * @param response HttpServletResponse needed for cookies and displaying the content
     * @param users Arraylist of all users to display
     * @param owner boolean true if the currentuser is an owner, needed to display the navigation buttons
     * @throws IOException
     */
    private void printUsers(HttpServletRequest request, HttpServletResponse response, ArrayList<User> users, boolean owner) throws IOException {
        Cookie[] cookies = request.getCookies();
        Cookie lastVisitDateCookie = null;
        Cookie timesVisitedCookie = null;

        //Check if the cookies already exist
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("timesVisited")) {
                timesVisitedCookie = cookie;
            } else if (cookie.getName().equals("lastVisitDate")) {
                lastVisitDateCookie = cookie;
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        //Create new cookies if they don't exist yet & Save data in the cookies.
        if (lastVisitDateCookie == null) {
            lastVisitDateCookie = new Cookie("lastVisitDate", dateFormat.format(date));
        } else {
            lastVisitDateCookie.setValue(dateFormat.format(date));
        }
        if (timesVisitedCookie == null) {
            timesVisitedCookie = new Cookie("timesVisited", "" + 1);
        } else {
            int timesVisited = Integer.parseInt(timesVisitedCookie.getValue()) + 1;
            timesVisitedCookie.setValue(timesVisited + "");
        }

        //Save the cookies
        response.addCookie(lastVisitDateCookie);
        response.addCookie(timesVisitedCookie);

        //Generate the HTML page
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head> \n" +
                "<title>Gevonden Kamers</title> \n" +
                "</head> \n" +
                "<body> \n" +
                "Laatste bezoek: " + lastVisitDateCookie.getValue() + " \n" +
                "Aantal keren bezocht: " + timesVisitedCookie.getValue() + "<br><br> \n");
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
                    "<input type=\"submit\" name=\"action\" value=\"Uitloggen\"> \n" +
                    "</form> \n");
        } else {
            out.println("<form action=\"/showPersons\" method=\"GET\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Kamer zoeken\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Uitloggen\"> \n" +
                    "</form> \n");
        }

        out.println("</body> \n" +
                "<!--created by TeamRetard's code--> \n" +
                "</html> \n");
    }
}
