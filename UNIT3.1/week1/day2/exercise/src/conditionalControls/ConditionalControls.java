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
}
