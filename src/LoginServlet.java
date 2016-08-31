import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Vincent on 8/31/2016.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Gebruiker gebruiker = findGebruiker(username);
        if (gebruiker == null) {
            System.out.println("User not found");
        } else {
            System.out.println(gebruiker.getGebruikersnaam() + " found");
            if (checkPassword(gebruiker, password)) {

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private Gebruiker findGebruiker(String username) {
        ArrayList<Gebruiker> gebruikers = GebruikerModel.getInstance().getGebruikers();
        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.getGebruikersnaam().equalsIgnoreCase(username)) {
                return gebruiker;
            }
        }

        return null;
    }

    private boolean checkPassword(Gebruiker gebruiker, String password) {
        return (gebruiker.getWachtwoord().equals(password));
    }


}
