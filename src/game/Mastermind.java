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

    /**
     * Deze functie checkt of er een valide input wordt gegeven en voert deze door
     * @return De lengte gekozen door de speler
     */
    public int lengteCheck()
    {
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

    /**
     * Genereert random kleuren op basis van de hier voor geselecteerde lengte
     * @param input (Lengte die de code moet worden)
     * @return geheimecodes (random kleuren, zoveel als de waarde van input)
     */
    public String[] genereerKleur(int input)
    {
        String[] geheimecodes = new String[input];
        for (int lengte = 0; lengte < geheimecodes.length; lengte++)
        {
            geheimecodes[lengte] = kleuren[rnd.nextInt(kleuren.length)];
        }
        return (geheimecodes);
    }

    /**
     * Checkt of de gok overeenkomt met een van de opgegeven kleuren, zo niet gaat hij een loopje in
     * @param y (optellende waarde in loop)
     * @param pogingen (aantal keren dat het programma je heeft laten gokken)
     * @param geheimecodes (de code van random kleuren)
     * @return (j/n of de gok overeenkomt met een valide antwoord)
     */
    public boolean goedeKleur(int y, String[] pogingen, String[] geheimecodes)
    {
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
                break;
            }
        }
        return matchFound;
    }

    /**
     * Het loopje die opnieuw blijft vragen om een valide input totdat deze aangeleverd wordt
     * @param matchFound (of er al een goede match was)
     * @param pogingen (de gegokte kleuren)
     * @param y (waarde die aangeeft hoevaak er geloopt is)
     * @return of er een goede match gevonden is
     */
    public boolean kleurenCheck(boolean matchFound, String[] pogingen, int y)
    {
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

    public void gewonnenOfVerloren(boolean gameGewonnen, String[] geheimecodes)
    {
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

    public int invoerCheck(String[] pogingen, String[] geheimeCodes, int score)
    {
        for (int o = 0; o < geheimeCodes.length; o++)
        {
            // Standaarwaarde word elke poging terug gezet naar fout
            String resultaat = "- ";
            if (pogingen[o].equals(geheimeCodes[o]))
            {
                //bij een 1 op 1 match goed
                resultaat = "Z ";
                score++;
            }
            else
            {
                for (int j = 0; j < geheimeCodes.length; j++)
                {
                    if (pogingen[j].equals(geheimeCodes[j]))
                    {
                        //als hij 1 en 1 wilt verglijken skipt hij dit en gaat naar 1 en 2 verglijken
                        continue;
                    }
                    if (pogingen[o].equals(geheimeCodes[j]))
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
        return score;
    }
}
