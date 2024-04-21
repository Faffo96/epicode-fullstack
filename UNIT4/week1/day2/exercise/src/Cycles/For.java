package Cycles;
import java.util.Scanner;

public class For {

    public static void countdown() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci un numero intero: ");
        int number = scanner.nextInt();

        for (int i = number; i >= 0; i--) {
            System.out.println(i);
        }

        System.out.println("Countdown terminato!");
    }
}
