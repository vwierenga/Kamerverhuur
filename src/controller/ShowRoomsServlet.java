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
 * Created by Vincent on 8/29/2016.
 */
@WebServlet("/controller.ShowRoomsServlet")
public class ShowRoomsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("login") != null && (Boolean) session.getAttribute("login")) {

            User currentUser = (User) session.getAttribute("currentUser");

            if(currentUser.isOwner()) {
                printRooms(response, findRooms(currentUser), true);
            } else{
                int size = Integer.parseInt(request.getParameter("size"));
                int price = Integer.parseInt(request.getParameter("price"));
                String city = request.getParameter("city");
                if(city != null){
                    printRooms(response, findRooms(size, price, city), false);
                } else {
                    System.out.println("Null city");
                    response.sendRedirect("/user.html");
                }
            }
        }
    }

    //Website navigation
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("login") != null && (Boolean) session.getAttribute("login")) {
            if ("Kamer toevoegen".equals(action)) {
                getServletContext().getRequestDispatcher("/WEB-INF/addroom.html").forward(request, response);
            } else if("Opnieuw zoeken".equals(action)) {
                getServletContext().getRequestDispatcher("/WEB-INF/huurder.html").forward(request, response);
            } else if ("Gebruikers".equals(action)) {
                getServletContext().getRequestDispatcher("/showPersons").forward(request, response);
            } else if ("Uitloggen".equals(action)) {
                session.setAttribute("login", new Boolean(false));  //Set false when logging out.
                getServletContext().getRequestDispatcher("/login.html").forward(request, response);
            }
        }
    }

    /**
     *
     * @param response HttpServletResponse needed to generate the HTML page
     * @param rooms Arraylist of all rooms to display
     * @param owner boolean true if the current user is an owner, needed to display the navigation buttons
     * @throws IOException
     */
    private void printRooms(HttpServletResponse response, ArrayList<Room> rooms, boolean owner) throws IOException {
        int kamernr = 0;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head> \n" +
                "<title>Gevonden Kamers</title> \n" +
                "</head> \n" +
                "<body> \n");
        if (rooms.size() > 0) {
            for(Room room : rooms) {
                kamernr++;
                out.println("Kamer " + kamernr + "<br> \n" +
                        "Grootte:  " + room.getSize() + "m2 <br> \n" +
                        "Prijs:  " + room.getPrice() + " euro <br> \n" +
                        "Adres: " + room.getAddress() + " " + room.getCity() + "<br> <br>\n");
            }
        } else {
            out.println("<font color = 'chucknorris'>U heeft geen kamers :(</font><br> <br> \n");
        }

        if(owner) {
            out.println("<form action=\"/showRooms\" method=\"GET\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Kamer toevoegen\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Gebruikers\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Uitloggen\"> \n" +
                    "</form> \n");
        } else {
            out.println("<form action=\"/showRooms\" method=\"GET\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Opnieuw zoeken\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Gebruikers\"> \n" +
                    "<input type=\"submit\" name=\"action\" value=\"Uitloggen\"> \n" +
                    "</form> \n");
        }

        out.println("</body> \n" +
                "<!--created by TeamRetard's code--> \n" +
                "</html> \n");
    }

    /**
     * Find rooms by a few requirements.
     * @param size The minimum size of the room.
     * @param price The maximum price of the room.
     * @param city The city the room should be located in.
     * @return Arraylist with rooms that meet the requirements
     */
    private ArrayList<Room> findRooms(int size, int price, String city) {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : (ArrayList<Room>) getServletContext().getAttribute("rooms")) {
            if (room.getPrice() <= price && room.getSize() >= size && room.getCity().equalsIgnoreCase(city)) {
                rooms.add(room);
            }
        }

        return rooms;
    }

    /**
     * Find room by owner.
     * @param user The owner of the rooms we're looking for.
     * @return Arraylist of rooms with the user as owner
     */
    private ArrayList<Room> findRooms(User user) {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : (ArrayList<Room>) getServletContext().getAttribute("rooms")) {
            if (room.getOwner() == user) {
                rooms.add(room);
            }
        }
        return rooms;
    }
}
