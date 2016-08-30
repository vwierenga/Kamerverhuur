import java.util.ArrayList;

/**
 * Created by Vincent on 8/30/2016.
 */
public class GebruikerModel {
    private static GebruikerModel ourInstance = new GebruikerModel();
    private ArrayList<Gebruiker> gebruikers = new ArrayList<>();

    public static GebruikerModel getInstance() {
        return ourInstance;
    }

    private GebruikerModel() {
    }
}
