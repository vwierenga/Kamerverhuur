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
import java.util.ArrayList;

/**
 * Created by Vincent on 9/9/2016.
 */
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        //Checks if you're logged in.
        if (session != null && session.getAttribute("login") != null && (Boolean) session.getAttribute("login")) {
            if ("Toevoegen".equals(action)) {
                User currentUser = (User) session.getAttribute("currentUser");
                if (currentUser.isOwner()) {
                    //adds the room to the arraylist.
                    int size = Integer.parseInt(request.getParameter("size"));
                    int price = Integer.parseInt(request.getParameter("price"));
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    if (address != null && city != null) {
                        ArrayList<Room> rooms = (ArrayList<Room>) getServletContext().getAttribute("rooms");
                        rooms.add(new Room(size, price, address, city, currentUser));
                    }
                    getServletContext().getRequestDispatcher("/showRooms").forward(request, response);
                }
            } else if("Kamer Overzicht".equals(action)) {
                //returns you to showRooms in case you changed your mind.
                getServletContext().getRequestDispatcher("/showRooms").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
