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

        int input = mm.lengteCheck();

        System.out.print("Top! de code is nu " + input + " kleuren lang.");

        String[] geheimecodes = mm.genereerKleur(input);


        // Loop 10 keer
        for (int i = 0; i < 10; i++)
        {

            ronde++;

            // Vraag voor user input
            System.out.println("\n\nPoging: " + ronde);

            String[] pogingen = new String[geheimecodes.length];

            for (int y = 0; y < geheimecodes.length; y++)
            {

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

                String resultaat = "- "; //standaarwaarde word elke poging terug gezet naar fout
                if (pogingen[o].equals(geheimecodes[o]))
                {
                    resultaat = "Z "; //bij een 1 to 1 match goed
                    score++;
                }
                else
                {
                    for (int j = 0; j < geheimecodes.length; j++)
                    {
                        if (pogingen[j].equals(geheimecodes[j]))
                        {
                            continue; //als hij 1 en 1 wilt verglijken skipt hij dit en gaat naar 1 en 2 verglijken
                        }
                        if (pogingen[o].equals(geheimecodes[j]))
                        {
                            resultaat = "W "; //als een van de andere kleuren overeenkomt met je gok wordt de aanwezigheid aangegeven met een W
                            break;
                        }
                    }
                }
                System.out.print(resultaat);
            }

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

        mm.wonOrlost(gameGewonnen, geheimecodes);
    }
}
