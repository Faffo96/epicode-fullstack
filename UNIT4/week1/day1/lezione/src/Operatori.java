package it.epicode;

import java.util.Scanner;

public class Operatori {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci un numero");
        int x = scanner.nextInt();

        System.out.println("Inserisci un altro numero");
        int y = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Inserisci una parola");
        String s =scanner.nextLine();

        System.out.println(s);

        int z = x%y;

        System.out.println(x%y);

        x++; //x=x+1

        System.out.println(x);

        boolean b1 = true;
        boolean b2 = false;

        System.out.println(b1 && b2);

        System.out.println(b1 || b2);

        System.out.println(!b1);

        int somma =somma(5,6);

        System.out.println(somma);

        concatena("java", 22);
    }

    public static int somma(int a, int b){
        int c = a+b;

        return c;
    }

    public static void concatena(String s, int a){
        System.out.println(s+a);
    }

//    public static int[] popolaArray(int[] numeri, String s){
//
//    }
}
