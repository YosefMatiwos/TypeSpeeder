package se.ju23.typespeeder;

import java.util.Scanner;
import java.util.Random;

public class Spellogik {
    private String[] ordListaLatt = {"hund", "katt", "fågel", "elefant", "apa"};
    private String[] ordListaMedel = {"bil", "flygplan", "båt", "cykel", "boll", "giraff", "fågel", "baka"};
    private String[] ordListaSvar = {"Genomskinlig", "Abonnemang", "Grupporträtt", "Kontinuerlig", "Pessimistisk"};
    private Scanner scanner = new Scanner(System.in);
    private SprakHantering sprakHantering; // Lägg till detta

    public Spellogik(SprakHantering sprakHantering) {
        this.sprakHantering = sprakHantering;
    }

    public void startSpel() {
        boolean fortsattSpel = true;

        while (fortsattSpel) {
            System.out.println(sprakHantering.getText("valjSvarighetsgrad"));
            System.out.println("1. " + sprakHantering.getText("latt"));
            System.out.println("2. " + sprakHantering.getText("medel"));
            System.out.println("3. " + sprakHantering.getText("svar"));
            System.out.println("4. " + sprakHantering.getText("gaTillbaka"));
            System.out.print(sprakHantering.getText("valjEttAlternativ") + ": ");
            int svårighetsgrad = scanner.nextInt();
            scanner.nextLine(); // konsumera ny rad

            String[] slumpOrd = null;
            switch (svårighetsgrad) {
                case 1:
                    slumpOrd = slumpOrd(ordListaLatt);
                    break;
                case 2:
                    slumpOrd = slumpOrd(ordListaMedel);
                    break;
                case 3:
                    slumpOrd = slumpOrd(ordListaSvar);
                    break;
                case 4:
                    fortsattSpel = false; // Gå tillbaka till menyn
                    break;
                default:
                    System.out.println(sprakHantering.getText("ogiltigtVal"));
                    continue;
            }

            if (!fortsattSpel) {
                break; // Avsluta spelet om användaren valde att gå tillbaka till menyn
            }

            System.out.print(sprakHantering.getText("skrivOrden") + ": ");
            for (String ord : slumpOrd) {
                System.out.print(ord + " ");
            }
            System.out.println();

            long startTime = System.currentTimeMillis();
            String anvandarensOrd = scanner.nextLine().trim();
            long endTime = System.currentTimeMillis();

            long tidKvar = (endTime - startTime) / 1000; // Omvandla till sekunder

            if (anvandarensOrd.equals(String.join(" ", slumpOrd))) {
                System.out.println(sprakHantering.getText("rattSkrivet") + tidKvar + " " + sprakHantering.getText("sekunder"));
            } else {
                System.out.println(sprakHantering.getText("felaktigtOrd"));
            }
        }
    }

    private String[] slumpOrd(String[] ordLista) {
        Random random = new Random();
        String[] slumpOrd = new String[ordLista.length];
        boolean[] valdaIndex = new boolean[ordLista.length];
        int index;
        for (int i = 0; i < slumpOrd.length; i++) {
            do {
                index = random.nextInt(ordLista.length);
            } while (valdaIndex[index]);
            slumpOrd[i] = ordLista[index];
            valdaIndex[index] = true;
        }
        return slumpOrd;
    }
}
