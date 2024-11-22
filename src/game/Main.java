package game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static String string;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean gameGewonnen = false;

        // Genereer code
        String[] kleuren = {"rood", "oranje", "geel", "groen", "blauw", "paars"};
        Random rnd = new Random();
        String geheimecodes[] = new String[4];
        geheimecodes[0] = kleuren[rnd.nextInt(kleuren.length)];
        geheimecodes[1] = kleuren[rnd.nextInt(kleuren.length)];
        geheimecodes[2] = kleuren[rnd.nextInt(kleuren.length)];
        geheimecodes[3] = kleuren[rnd.nextInt(kleuren.length)];

        int score = 0;

        int ronde = 0;

        System.out.println("\nWelkom bij CodeBreaker");
        System.out.println("U kunt per beurt gokken uit de kleuren:");
        System.out.println("(let op, geen hoofdletters)");
        System.out.println("rood, oranje, geel, groen, blauw, paars");

        // Loop 10 keer
        for (int i = 0; i < 10; i++) {

            ronde++;

            // Vraag voor user input
            System.out.println("\n\nPoging: " + ronde);
            System.out.print("Voer uw gok in: ");


            String pogingen[] = {sc.next(), sc.next(), sc.next(), sc.next()};

            // Controleer eerste poging
            for (int o = 0; o < 4; o++) {

                String resultaat = "- ";
                if (pogingen[o].equals(geheimecodes[o])) {
                    resultaat = "Z ";
                    score++;
                } else {
                    for (int j = 0; j < 4; j++) {
                        if (j == o) {
                            continue;
                        }
                        if (pogingen[o].equals(geheimecodes[j])) {
                            resultaat = "W ";
                            break;
                        }
                    }
                }
                System.out.print(resultaat);
            }

            if (score == 4) {
                i = 11;
                gameGewonnen = true;
            } else {
                score = 0;
            }
        }
        System.out.println();
        if (gameGewonnen) {
            System.out.println("\nGefeliciteerd je hebt gewonnen!!!");
        } else {
            System.out.println("\nHelaas, de code was:" + geheimecodes[0] + " " + geheimecodes[1] + " " + geheimecodes[2] + " " + geheimecodes[3]);
        }
    }
}
