package game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Mastermind
{
    String[] kleuren = {"rood", "oranje", "geel", "groen", "blauw", "paars"};
    Random rnd = new Random();
    Scanner sc = new Scanner(System.in);
    String resultaat = "- ";

    public int lengteCheck() {
        int input = 0;
        boolean goedeinput = false;

        while (!goedeinput)
        {
            try
            {
                input = sc.nextInt();
                if (input > 0)
                {
                    goedeinput = true;
                }
                else
                {
                    System.out.println("Oeps je kan geen negatief getal invullen");
                    System.out.print("probeer het opnieuw: ");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Oeps, u heeft iets verkeerds ingevult");
                System.out.print("Vul aub een nummer in voor de lengte van de code: ");
                sc.next();
            }
        }
        return (input);
    }
    public String[] genereerKleur(int input) {
        String[] geheimecodes = new String[input];
        for (int lengte = 0; lengte < geheimecodes.length; lengte++)
        {
            geheimecodes[lengte] = kleuren[rnd.nextInt(kleuren.length)];
        }
        return (geheimecodes);
    }

    public boolean goedeInput(int y, String[] pogingen, String[] geheimecodes) {
    boolean matchFound = false;

                y++;
                System.out.print("Kleur " + y + ": ");
                y--;


                pogingen[y] = sc.next().toLowerCase();
                for (String str : kleuren)
                {
                    if (pogingen[y].equals(str))
                    {
                        matchFound = true;
                        break; //hoe staat Robert tov de break, ik vind dat je dan een while moet gebruiken dan heb je die niet nodig
                    }
                }
                return matchFound;
}
    public boolean verkeerdeInput(boolean matchFound, String[] pogingen, int y) {
        for (String str : kleuren)
        {
            if (pogingen[y].equals(str))
            {
                matchFound = true;
                break;
            }
        }
        return matchFound;
    }

    public void wonOrlost(boolean gameGewonnen, String[] geheimecodes) {
        if (gameGewonnen)
        {
            System.out.println("\nGefeliciteerd je hebt gewonnen!!!");
        }
        else
        {
            System.out.print("\nHelaas, de code was: ");
            for (String geheimecode : geheimecodes)
            {
                System.out.print(geheimecode + " ");

            }
        }
    }
}
