package game;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        int attempts = 0;

        Scanner sc = new Scanner(System.in);

        int[] code = { rand.nextInt(7), rand.nextInt(7), rand.nextInt(7), rand.nextInt(7) };

        int score = 0;

        boolean validGuess = true;

        // Main Game Loop
        while (attempts < 10) {
            System.out.println("Numbers: 0-7");
            System.out.println("Attempt " + (attempts += 1) + ": Enter your guess: (example: 0 1 2 3) ");

            // automatic debugging on mismatch input and invalid numbers
            try {
                int[] guess = { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };
                validGuess = true;
                for (int i = 0; i < 4; i++) {
                    if (guess[i] > 7) {
                        System.out
                                .println("Number higher then 7 detected, try again with numbers in between 0 and 7.\n");
                        attempts -= 1;
                        validGuess = false;
                        break;
                    }
                    if (guess[i] < 0) {
                        System.out.println("Number lower then 0 detected, try again with numbers in between 0 and 7.\n");
                        attempts -= 1;
                        validGuess = false;
                        break;
                    }
                }
                // checking the code
                if (validGuess) {
                    for (int o = 0; o < 4; o++) {

                        String feedback = "- ";
                        if (guess[o] == code[o]) {
                            feedback = "Black ";
                            score++;
                        } else {
                            for (int j = 0; j < 4; j++) {
                                if (j == o) {
                                    continue;
                                }
                                if (guess[o] == code[j]) {
                                    feedback = "white ";
                                    break;
                                }
                            }
                        }
                        System.out.print(feedback);
                    }

                    // shortcut to exit loop if you have all numbers correct
                    if (score == 4)

                    {
                        attempts = 10;
                    } else {
                        score = 0;
                    }
                }

                // catching a mistake made by writing down a different variable instead of an
                // integer
            } catch (InputMismatchException e) {
                System.out.println("\nWrong input. Try placing 4 numbers divided by spaces.");
                sc.nextLine();
                attempts -= 1;
                continue;
            }
        }
        // checking to see if you broke the loop by attempts, or by winning
        if (score == 4) {
            System.out.println("\nYou have won! good job :D");
        } else {
            System.out.println("\nYou have sadly lost, better luck next time! The code was: " + code[0] + "-" + code[1]
                    + "-" + code[2] + "-" + code[3]);

        }
        sc.close();
    }

}