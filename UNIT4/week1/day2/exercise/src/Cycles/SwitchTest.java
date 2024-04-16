package Cycles;
import java.util.Scanner;


    public class SwitchTest {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Inserisci un numero intero tra 0 e 3: ");
            int number = scanner.nextInt();

            switch (number) {
                case 0:
                    System.out.println("Il numero inserito è zero.");
                    break;
                case 1:
                    System.out.println("Il numero inserito è uno.");
                    break;
                case 2:
                    System.out.println("Il numero inserito è due.");
                    break;
                case 3:
                    System.out.println("Il numero inserito è tre.");
                    break;
                default:
                    System.out.println("Errore: il numero inserito non è compreso tra 0 e 3.");
            }
        }
    }

