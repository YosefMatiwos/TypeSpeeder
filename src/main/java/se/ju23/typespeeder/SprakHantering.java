package se.ju23.typespeeder;

import java.util.HashMap;
import java.util.Map;

public class SprakHantering {
    private Map<String, String> svenska;
    private Map<String, String> engelska;
    private String aktuelltSprak = "Svenska"; // Standardvärde

    public SprakHantering() {
        svenska = new HashMap<>();
        engelska = new HashMap<>();

        // Grundläggande menyval
        svenska.put("välkommen", "Välkommen! Välj ett alternativ:");
        engelska.put("välkommen", "Welcome! Choose an option:");
        svenska.put("loggaIn", "Logga in");
        engelska.put("loggaIn", "Log in");
        svenska.put("registreraDig", "Registrera dig");
        engelska.put("registreraDig", "Sign up");
        svenska.put("avsluta", "Avsluta");
        engelska.put("avsluta", "Exit");

        // Spelrelaterade strängar
        svenska.put("valjSvarighetsgrad", "Välj svårighetsgrad:");
        engelska.put("valjSvarighetsgrad", "Choose difficulty level:");
        svenska.put("latt", "Lätt");
        engelska.put("latt", "Easy");
        svenska.put("medel", "Medel");
        engelska.put("medel", "Medium");
        svenska.put("svar", "Svår");
        engelska.put("svar", "Hard");
        svenska.put("gaTillbaka", "Gå tillbaka till menyn");
        engelska.put("gaTillbaka", "Go back to the menu");
        svenska.put("valjEttAlternativ", "Välj ett alternativ");
        engelska.put("valjEttAlternativ", "Choose an option");
        svenska.put("ogiltigtVal", "Ogiltigt val. Försök igen.");
        engelska.put("ogiltigtVal", "Invalid choice. Please try again.");
        svenska.put("skrivOrden", "Skriv orden");
        engelska.put("skrivOrden", "Type the words");
        svenska.put("rattSkrivet", "Du skrev rätt! Det tog ");
        engelska.put("rattSkrivet", "You typed correctly! It took ");
        svenska.put("sekunder", " sekunder.");
        engelska.put("sekunder", " seconds.");
        svenska.put("felaktigtOrd", "Felaktigt ord!");
        engelska.put("felaktigtOrd", "Incorrect word!");

        // Fler strängar kan läggas till här efter behov
    }

    public void setAktuelltSprak(String sprak) {
        this.aktuelltSprak = sprak;
    }

    public String getText(String nyckel) {
        switch (aktuelltSprak) {
            case "Svenska":
                return svenska.getOrDefault(nyckel, "Okänd");
            case "Engelska":
                return engelska.getOrDefault(nyckel, "Unknown");
            default:
                return "Okänd";
        }
    }
}