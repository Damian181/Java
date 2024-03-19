
package bullscows;

import java.util.*;

public class Main {
    public static int bulls = 0;
    public static  int cows = 0;


    public static String randomGenerator(int length, int symbols) {
        List<Character> randomList = new ArrayList<>(List.of('0','1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
        StringBuilder possibleSymbols = new StringBuilder();
        List<Character> lengthOfSymbols = new ArrayList<>();
        for (var ch : randomList.subList(0,symbols)) {
            lengthOfSymbols.add(ch);
            possibleSymbols.append(ch);
                if (ch == symbols) {
                    break;
                }
        }

        Collections.shuffle(lengthOfSymbols);
        StringBuilder stringBuilder = new StringBuilder();
        for (var ch : lengthOfSymbols.subList(0, length)) {
            stringBuilder.append(ch);
        }
        System.out.println("The secret is prepared: " +
                "*".repeat(Math.max(0, length)) + " (" + possibleSymbols.charAt(0) + "-" +possibleSymbols.charAt(symbols - 1) + ")");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");
        int secretCodeLength = 0;
        try {
            secretCodeLength = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: invalid number.");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        int numberOfPossibleSymbols = sc.nextInt();
        if (numberOfPossibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-z).");
            System.exit(0);
        }

        if (numberOfPossibleSymbols < secretCodeLength || secretCodeLength == 0) {
            System.out.println("Error: it's not possible to generate a code with a length of "+secretCodeLength+
                    " with "+numberOfPossibleSymbols+" unique symbols.");
            System.exit(0);
        }


        String secretCode = randomGenerator(secretCodeLength, numberOfPossibleSymbols);
        System.out.println("Okay, let's start a game!");

        for (int i = 1; i < 9999; i++) {
            System.out.println("turn " + i + ':');
            String input = sc.next();

            for (int j = 0; j < secretCode.length(); j++) {
                for (int k = 0; k < input.length(); k++) {
                    if (input.charAt(k) == secretCode.charAt(j)) {
                        if (j == k) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }

            }  if (bulls == secretCodeLength){
                System.out.println("Grade: " + bulls + " bulls ");
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            if (cows > 0 && bulls > 0) {
                System.out.println("Grade: " + bulls + " bull and " + cows + " cow ");
                cows = 0;
                bulls = 0;

            } else if (cows > 0) {
                System.out.println("Grade: " + cows + " cow ");
                cows = 0;

            } else if (bulls > 0) {
                System.out.println("Grade: " + bulls + " bull ");
                bulls = 0;

            } else if (bulls == 0 && cows == 0) {
                System.out.println("Grade: " + bulls + " bull and " + cows + " cow ");
            }
        }
    }
}