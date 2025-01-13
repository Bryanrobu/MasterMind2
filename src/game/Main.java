package game;

import java.util.Random;
import java.util.Scanner;

public class Main
{
    private static String string;

    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        Mastermind mm = new Mastermind();
        Random rnd = new Random();

        boolean gameGewonnen = false;

        // Maak kleuren aan voor de code
        String[] kleuren = {"rood", "oranje", "geel", "groen", "blauw", "paars"};

        int score = 0;

        int ronde = 0;

        // Print de uitleg
        System.out.println("\nWelkom bij CodeBreaker");
        System.out.println("U kunt per beurt gokken uit de kleuren:");
        System.out.println("rood, oranje, geel, groen, blauw, paars");
        System.out.print("voer in hoelang je de code wilt hebben: ");

        // Genereer code
        int input = mm.lengteCheck();

        System.out.print("Top! de code is nu " + input + " kleuren lang.");

        String[] geheimecodes = mm.genereerKleur(input);


        // Loop 10 keer
        for (int i = 0; i < 10; i++)
        {

            ronde++;

            // Print het poging nummer en maak een lege array aan voor de pogingen
            System.out.println("\n\nPoging: " + ronde);

            String[] pogingen = new String[geheimecodes.length];

            for (int y = 0; y < geheimecodes.length; y++)
            {
                // Hij gaat vragen om je user input, zodra je iets wat geen optie was invult
                // Gaat hij het opnieuw vragen tot je iets goeds invult
                boolean matchFound = mm.goedeInput(y, pogingen, geheimecodes);

                while (!matchFound)
                {
                    System.out.println("Vul een van de bovenstaande kleuren in aub");
                    y++;
                    System.out.print("Kleur " + y + ": ");
                    y--;
                    pogingen[y] = sc.next();

                    matchFound = mm.verkeerdeInput(matchFound, pogingen, y);
                }
            }


            // Controleer eerste poging
            for (int o = 0; o < geheimecodes.length; o++)
            {
                // Standaarwaarde word elke poging terug gezet naar fout
                String resultaat = "- ";
                if (pogingen[o].equals(geheimecodes[o]))
                {
                    //bij een 1 op 1 match goed
                    resultaat = "Z ";
                    score++;
                }
                else
                {
                    for (int j = 0; j < geheimecodes.length; j++)
                    {
                        if (pogingen[j].equals(geheimecodes[j]))
                        {
                            //als hij 1 en 1 wilt verglijken skipt hij dit en gaat naar 1 en 2 verglijken
                            continue;
                        }
                        if (pogingen[o].equals(geheimecodes[j]))
                        {
                            //als een van de andere kleuren overeenkomt met je gok wordt de aanwezigheid aangegeven met een W
                            resultaat = "W ";
                            break;
                        }
                    }
                }
                //Hier print hij dus Z, W of -
                System.out.print(resultaat);
            }

            //Als de score gelijk is aan de lengte van de code (je krijgt 1 score per goed antwoord, dus alles goed)
            //Dan heb je het spel gewonnen, en zet hij de waarde i op 11 zodat je uit de main game loop breekt
            //Zo niet gaat de score van goede antwoorden terug naar 0
            if (score == geheimecodes.length)
            {
                i = 11;
                gameGewonnen = true;
            }
            else
            {
                score = 0;
            }
        }
        System.out.println();

        //Hij checkt of je score gelijk was aan de lengte va de code of dat je over de 10 pogingen heen bent gegaan
        //(hij bepaald dus of je hebt gewonnen of verloren)
        mm.gewonnenOfVerloren(gameGewonnen, geheimecodes);
    }
}
