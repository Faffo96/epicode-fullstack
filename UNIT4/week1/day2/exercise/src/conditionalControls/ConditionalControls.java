package conditionalControls;

public class ConditionalControls {

    public static boolean pariDispari(String stringa) {
        int lunghezza = stringa.length();
        return lunghezza % 2 == 0;
    }

    public static boolean annoBisestile(int anno) {
        if (anno % 400 == 0) {
            return true;
        } else if (anno % 100 == 0) {
            return false;
        } else if (anno % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String stringa = "Hello, World!";
        int anno = 2024;

        if (pariDispari("Hello, World!")) {
            System.out.println("La stringa ha un numero di caratteri pari.");
        } else {
            System.out.println("La stringa ha un numero di caratteri dispari.");
        }

        if (annoBisestile(2024)) {
            System.out.println("L'anno è un anno bisestile.");
        } else {
            System.out.println("L'anno non è un anno bisestile.");
        }
    }
}
