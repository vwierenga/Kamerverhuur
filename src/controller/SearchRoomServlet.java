package controller;

import model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Vincent on 8/29/2016.
 */
@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("login") != null && (Boolean) session.getAttribute("login")) {
            //user is logged in
            int size = Integer.parseInt(request.getParameter("size"));
            int price = Integer.parseInt(request.getParameter("price"));
            String city = request.getParameter("city");

            if (city != null) {
                ArrayList<Room> rooms = findRooms(size, price, city);
                int kamernr = 0;
                if (rooms.size() > 0) {
                    kamernr++;
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println(
                            "<head> \n" +
                            "<title>Gevonden Kamers</title> \n" +
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
                    out.println(
                            "<head> \n" +
                            "<title>Geen kamers gevonden</title> \n" +
                            "</head> \n" +
                            "<body> \n" +
                            "<font color = 'chucknorris'>Geen kamers gevonden!</font><br> <br> \n" +
                            "<a href='login.html'>login</a> \n" +
                            "</body> \n" +
                            "<!--created by TeamRetard's code--> \n" +
                            "</html> \n");
                }
            }
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(
                    "<head> \n" +
                    "<title>Niet ingelogd</title> \n" +
                    "</head> \n" +
                    "<body> \n" +
                    "U bent <font color = 'chucknorris'>niet ingelogd!</font><br> \n" +
                    "Log <a href='login.html'>hier</a> aub in. <br><br> \n" +
                    "</body> \n" +
                    "<!--created by TeamRetard's code--> \n" +
                    "</html> \n");
        }
    }

    private ArrayList<Room> findRooms(int size, int price, String city) {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : (ArrayList<Room>) getServletContext().getAttribute("rooms")) {
            if (room.getPrice() <= price && room.getSize() >= size && room.getCity().equalsIgnoreCase(city)) {
                rooms.add(room);
            }
        }

        return rooms;
    }
}
