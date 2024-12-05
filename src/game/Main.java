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

        int score = 0;

        int ronde = 0;

        System.out.println("\nWelkom bij CodeBreaker");
        System.out.println("U kunt per beurt gokken uit de kleuren:");
        System.out.println("rood, oranje, geel, groen, blauw, paars");
        System.out.print("voer in hoelang je de code wilt hebben: ");

        int input = 0;
        boolean goedeinput = false;

        while (!goedeinput) {
            try {
                input = sc.nextInt();
                if (input > 0) {
                    goedeinput = true;
                } else {
                    System.out.println("Oeps je kan geen negatief getal invullen,");
                    System.out.print("probeer het opnieuw: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Oeps, u heeft iets verkeerds ingevult");
                System.out.print("Vul aub een nummer in voor de lengte van de code: ");
                sc.next();
            }
        }


        System.out.print("Top! de code is nu " + input + " kleuren lang.");

        String[] geheimecodes = new String[input];
        for (int lengte = 0; lengte < geheimecodes.length; lengte++) {
            geheimecodes[lengte] = kleuren[rnd.nextInt(kleuren.length)];
        }

        // Loop 10 keer
        for (int i = 0; i < 10; i++) {

            ronde++;

            // Vraag voor user input
            System.out.println("\n\nPoging: " + ronde);
//            System.out.print("Voer uw gok in: ");
//
//
            String[] pogingen = new String[geheimecodes.length];
//            for (int lengte = 0; lengte < geheimecodes.length; lengte++) {
//                pogingen[lengte] = sc.next();
//            }


            for (int y = 0; y < geheimecodes.length; y++) {
                boolean matchFound = false;
                y++; System.out.print("Kleur " + y + ": "); y--;
                pogingen[y] = sc.next().toLowerCase();
                for (String str : kleuren) {
                    if (pogingen[y].equals(str)) {
                        matchFound = true;
                        break;
                    }
                }
                while (!matchFound) {
                    System.out.println("Vul een van de bovenstaande kleuren in aub");
                    y++; System.out.print("Kleur " + y + ": "); y--;
                    pogingen[y] = sc.next();
                    for (String str : kleuren) {
                        if (pogingen[y].equals(str)) {
                            matchFound = true;
                            break;
                        }
                    }
                }
            }



            // Controleer eerste poging
            for (int o = 0; o < geheimecodes.length; o++) {

                String resultaat = "- "; //standaarwaarde word elke poging terug gezet naar fout
                if (pogingen[o].equals(geheimecodes[o])) {
                    resultaat = "Z "; //bij een 1 to 1 match goed
                    score++;
                } else {
                    for (int j = 0; j < geheimecodes.length; j++) {

                        if (pogingen[j].equals(geheimecodes[j])) {
                            continue; //als hij 1 en 1 wilt verglijken skipt hij dit en gaat naar 1 en 2 verglijken
                        }

                        if (pogingen[o].equals(geheimecodes[j])) {
                            resultaat = "W "; //als een van de andere kleuren overeenkomt met je gok wordt de aanwezigheid aangegeven met een W
                            break;
                        }
                    }
                }
                System.out.print(resultaat);
            }

            if (score == geheimecodes.length) {
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
            System.out.print("\nHelaas, de code was: ");
            for (String geheimecode : geheimecodes) {
                System.out.print(geheimecode + " ");

            }
        }
    }
}
