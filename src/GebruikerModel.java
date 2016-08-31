import java.util.ArrayList;

/**
 * Created by Vincent on 8/30/2016.
 */
public class GebruikerModel {
    private static GebruikerModel ourInstance = new GebruikerModel();
    private ArrayList<Gebruiker> gebruikers;

    public static GebruikerModel getInstance() {
        return ourInstance;
    }

    private GebruikerModel() {
        gebruikers = new ArrayList<>();
        createTestGebruikers();
    }

    public ArrayList<Gebruiker> getGebruikers() {
        return new ArrayList<>(gebruikers);
    }

    public void addGebruiker(Gebruiker gebruiker) {
        gebruikers.add(gebruiker);
    }

    private void createTestGebruikers() {
        gebruikers.add(new Gebruiker("huurder", "huurder", true));
        gebruikers.add(new Gebruiker("verhuurder", "verhuurder", false));
    }
}
