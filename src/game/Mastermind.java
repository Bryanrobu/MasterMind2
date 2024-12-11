package game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Mastermind
{
    String[] kleuren = {"rood", "oranje", "geel", "groen", "blauw", "paars"};
    Random rnd = new Random();
    Scanner sc = new Scanner(System.in);

    public int lengtecheck() {
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
                    System.out.println("Oeps je kan geen negatief getal invullen,");
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
    public String[] GenerateColour(int input) {
        String[] geheimecodes = new String[input];
        for (int lengte = 0; lengte < geheimecodes.length; lengte++)
        {
            geheimecodes[lengte] = kleuren[rnd.nextInt(kleuren.length)];
        }
        return (geheimecodes);
    }
}
