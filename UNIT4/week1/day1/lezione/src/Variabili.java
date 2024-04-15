package it.epicode;

public class Variabili {
    public static void main(String[] args) {
        int x; //dichiarazione di variabile
        x=4; //assegnazione variabile
        x=6;

        boolean b = true; //dichiarazione ed assegnazione

        char c = 'f';

        double d = 3.3;

        String s = "benvenuti al corso epicode";

        String fraseConcatenata = s + " " + (x + 4);

        System.out.println(fraseConcatenata);

        final int y = 3; // variabile costante non più modificabile

        byte b2 = 100;

        x = b2; //conversione implicita

        System.out.println(x);

        //b2 = x; //conversione non ammessa

        x = 300;

        b2 = (byte)x; //cast esplicito

        System.out.println(b2);

        int[] numeri = new int[6];

        numeri[0]=3;
        numeri[1]=6;

        System.out.println(numeri[2]);

        String[] parole = new String[3];

        parole[0] = "corso";

        System.out.println(parole[1]);

        System.out.println(parole.length);

        //parole[3]="ciao";

        int[] numeri2 = {3,5,7};



    }


}
