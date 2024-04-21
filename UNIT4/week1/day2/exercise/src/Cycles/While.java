package Cycles;
import java.util.Scanner;

public class While {

    public static void whilee() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals(":q")) {
            System.out.println("Inserisci una stringa separata da virgole. Per interrompere inserire :q");
            input = scanner.nextLine();

            if (!input.equals(":q")) {
                String[] parts = input.split(",");
                for (String part : parts) {
                    System.out.print(part + " ");
                }
                System.out.println();
            }
        }

        System.out.println("Programma terminato.");
    }
}