package se.ju23.typespeeder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anvandarhantering {

    private Map<String, String> anvandaruppgifter;
    private Scanner scanner;
    private boolean inloggad;
    private String inloggadAnvandarnamn;
    private SprakHantering sprakHantering;



    public Anvandarhantering() {
        anvandaruppgifter = new HashMap<>();
        scanner = new Scanner(System.in);
        inloggad = false;
        sprakHantering = new SprakHantering(); // Initialisera här
    }

    public void start() {
        valjSprak(); // Låt användaren välja språk direkt vid start
        boolean fortsatt = true;
        while (fortsatt) {
            System.out.println(sprakHantering.getText("välkommen"));
            System.out.println("1. Logga in");
            System.out.println("2. Registrera dig");
            System.out.println("3. Avsluta");

            int val = scanner.nextInt();
            scanner.nextLine(); // För att konsumera ny rad

            switch (val) {
                case 1:
                    loggaIn();
                    break;
                case 2:
                    registrera();
                    break;
                case 3:
                    fortsatt = false;
                    break;
                default:
                    System.out.println("Ogiltigt val. Vänligen försök igen.");
            }
        }
    }

    public void valjSprak() {
        System.out.println("1. Svenska");
        System.out.println("2. Engelska");
        System.out.print("Välj ett språk: ");
        int val = scanner.nextInt();
        scanner.nextLine(); // Konsumera ny rad

        String sprak = (val == 1) ? "Svenska" : "Engelska";
        sprakHantering.setAktuelltSprak(sprak);
    }

    private void loggaIn() {
        System.out.println("Ange ditt användarnamn:");
        String anvandarnamn = scanner.nextLine();
        System.out.println("Ange ditt lösenord:");
        String losenord = scanner.nextLine();

        if (anvandaruppgifter.containsKey(anvandarnamn) && anvandaruppgifter.get(anvandarnamn).equals(losenord)) {
            inloggad = true;
            inloggadAnvandarnamn = anvandarnamn;
            System.out.println("Inloggning lyckades!");
            visaMeny();
        } else {
            System.out.println("Fel användarnamn eller lösenord.");
        }
    }

    private void registrera() {
        System.out.println("Ange ett användarnamn:");
        String anvandarnamn = scanner.nextLine();
        System.out.println("Ange ett lösenord:");
        String losenord = scanner.nextLine();
        System.out.println("Ange ett spelkontonamn:");
        String spelkontonamn = scanner.nextLine(); // Läs in spelkontonamn från användaren

        if (anvandaruppgifter.containsKey(anvandarnamn)) {
            System.out.println("Användarnamnet är redan taget. Vänligen välj ett annat.");
        } else {
            anvandaruppgifter.put(anvandarnamn, losenord);
            // Lägg till spelkontonamn till användaruppgifterna
            anvandaruppgifter.put("spelkontonamn_" + anvandarnamn, spelkontonamn);
            sparaAnvandaruppgifter();
            System.out.println("Registrering lyckades!");
        }
    }

    private void sparaAnvandaruppgifter() {
        try {
            FileWriter writer = new FileWriter("anvandare.txt");
            for (Map.Entry<String, String> entry : anvandaruppgifter.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visaMeny() {
        while (inloggad) {
            System.out.println("Välkommen!");
            System.out.println("1. Spela spel");
            System.out.println("2. Uppdatera användarinformation");
            System.out.println("3. Visa rankinglista");
            System.out.println("4. Nyheter och uppdateringar");
            System.out.println("5. Logga ut");
            System.out.print("Välj ett alternativ: ");

            int val = scanner.nextInt();
            scanner.nextLine(); // För att konsumera ny rad

            switch (val) {
                case 1:
                    startaSpel();
                    break;
                case 2:
                    uppdateraAnvandarinformation();
                    break;
                case 3:
                    // Lägg till koden för att visa rankinglista här
                    break;
                case 4:
                    // Lägg till koden för att visa nyheter och uppdateringar här
                    break;
                case 5:
                    inloggad = false;
                    System.out.println("Du är nu utloggad.");
                    break;
                default:
                    System.out.println("Ogiltigt val. Vänligen försök igen.");
            }
        }
    }

    private void uppdateraAnvandarinformation() {
        System.out.println("Vad vill du uppdatera?");
        System.out.println("1. Lösenord");
        System.out.println("2. Spelkontonamn");

        int val = scanner.nextInt();
        scanner.nextLine(); // För att konsumera ny rad

        switch (val) {
            case 1:
                uppdateraLosenord();
                break;
            case 2:
                uppdateraSpelkontonamn();
                break;
            default:
                System.out.println("Ogiltigt val. Vänligen försök igen.");
        }
    }

    private void uppdateraLosenord() {
        System.out.println("Ange det nuvarande lösenordet:");
        String nuvarandeLosenord = scanner.nextLine();

        String sparadeLosenord = anvandaruppgifter.get(inloggadAnvandarnamn);

        if (sparadeLosenord.equals(nuvarandeLosenord)) {
            System.out.println("Ange det nya lösenordet:");
            String nyttLosenord = scanner.nextLine();
            anvandaruppgifter.put(inloggadAnvandarnamn, nyttLosenord);
            sparaAnvandaruppgifter();
            System.out.println("Lösenordet har uppdaterats.");
        } else {
            System.out.println("Fel lösenord. Uppdateringen avbruten.");
        }
    }

    private void uppdateraSpelkontonamn() {
        System.out.println("Ange det nya spelkontonamnet:");
        String nyttSpelkontonamn = scanner.nextLine();
        anvandaruppgifter.put("spelkontonamn_" + inloggadAnvandarnamn, nyttSpelkontonamn);
        sparaAnvandaruppgifter();
        System.out.println("Spelkontonamnet har uppdaterats.");
    }

    private void startaSpel() {
        Spellogik spel = new Spellogik(sprakHantering); // Uppdaterad för att skicka med språkhanteringen
        spel.startSpel();
    }
    private void loggaUt() {
        inloggad = false;
        System.out.println("Du är nu utloggad.");
    }
}



