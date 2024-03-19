package se.ju23.typespeeder;

public class Main {
    public static void main(String[] args) {
        Anvandarhantering anvandarhantering = new Anvandarhantering();
        anvandarhantering.start();


        Spellogik spel = new Spellogik(new SprakHantering());
        spel.startSpel();
    }
}
