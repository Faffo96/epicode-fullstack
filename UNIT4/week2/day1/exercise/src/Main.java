import ArrayRandom.Random10;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    static Logger logger = LoggerFactory.getLogger("logger1");

    // TRACE<DEBUG<INFO<WARN<ERROR
    public static void main(String[] args) {
        logger.info("Launching app..");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Your random numbers:");

        Random10 a1 = new Random10();

        for (int i = 0; i < a1.getArray().length; i++) {
            System.out.println(a1.getArray()[i]);
        }

        System.out.println("Do you want to change one? (Y/N)");

        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("Y")) {
            int newNumber = 1;
            do {
                try {
                    System.out.println("Which position do you want change? (0-4)");
                    int i = scanner.nextInt();
                    scanner.nextLine();
                    if (i < 0 || i > 4) {
                        throw new IllegalArgumentException("Input must be between 0 and 4.");
                    }

                    System.out.println("Insert the new number: (0 will end the program.)");
                    newNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (newNumber != 0) {
                        a1.replaceArrayI(i, newNumber);

                        System.out.println("Do you want to change an other one? (Y/N)");
                        response = scanner.nextLine();
                        if (response.equalsIgnoreCase("N")) {
                            newNumber = 0;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("Please enter a number between 0 and 4.");
                    scanner.nextLine();
                }
            } while (newNumber != 0);
        } else {
            System.out.println("No changes made.");
        }
        System.out.println("Your new random numbers:");

        for (int i = 0; i < a1.getArray().length; i++) {
            System.out.println(a1.getArray()[i]);
        }


        scanner.close();
        logger.info("Closing app..");
    }
}
