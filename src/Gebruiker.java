/**
 * Created by Vincent on 8/30/2016.
 */
public class Gebruiker {
    private String gebruikersnaam;
    private String wachtwoord;
    private boolean huurder; //Als huurder false is dan is de gebruiker een verhuurder

    public Gebruiker(String gebruikersnaam, String wachtwoord, boolean huurder) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.huurder = huurder;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public boolean isHuurder() {
        return huurder;
    }
}

