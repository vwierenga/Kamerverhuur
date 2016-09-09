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

                if(user.isOwner()) {
                    printRooms(response, user);
                } else {
                    getServletContext().getRequestDispatcher("/WEB-INF/huurder.html").forward(request, response);
                }
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

    private ArrayList<Room> findRooms(User user) {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : (ArrayList<Room>) getServletContext().getAttribute("rooms")) {
            if (room.getOwner() == user) {
                rooms.add(room);
            }
        }
        return rooms;
    }

    private void printRooms(HttpServletResponse response, User user) throws IOException {
        ArrayList<Room> rooms = findRooms(user);
        int kamernr = 0;
        if (rooms.size() > 0) {
            kamernr++;
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(
                    "<head> \n" +
                    "<title>" + user.getUsersname() + "'s Kamers</title> \n" +
                    "</head> \n" +
                    "<body> \n");
            for(Room room : rooms) {
                out.println("Kamer " + kamernr + "<br> \n" +
                        "Grootte:  " + room.getSize() + "m2 <br> \n" +
                        "Prijs:  " + room.getPrice() + " euro <br> \n" +
                        "Adres: " + room.getAddress() + " " + room.getCity() + "<br> <br>\n");
            }
            out.println("<a href='login.html'>login</a> \n" +
                    "</body> \n" +
                    "<!--created by TeamRetard's code--> \n" +
                    "</html> \n");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<head> \n" +
                    "<title>" + user.getUsersname() + "'s Kamers</title> \n" +
                    "</head> \n" +
                    "<body> \n" +
                    "<font color = 'chucknorris'>U heeft geen kamers :(</font><br> <br> \n" +
                    "<a href='login.html'>login</a> \n" +
                    "</body> \n" +
                    "<!--created by TeamRetard's code--> \n" +
                    "</html> \n");
        }
    }


}
