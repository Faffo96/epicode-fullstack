import Cycles.For;
import Cycles.SwitchTest;
import Cycles.While;
import conditionalControls.ConditionalControls;

import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("CONDITIONAL CONTROLS EXERCISE: ");

        String stringa = "Hello, World!";
        int anno = 2024;

        if (ConditionalControls.pariDispari("Hello, World!")) {
            System.out.println("La stringa ha un numero di caratteri pari.");
        } else {
            System.out.println("La stringa ha un numero di caratteri dispari.");
        }

        if (ConditionalControls.annoBisestile(2024)) {
            System.out.println("L'anno è un anno bisestile.");
        } else {
            System.out.println("L'anno non è un anno bisestile.");
        }

        System.out.println("CYCLES EXERCISE 1: ");

        For.countdown();

        System.out.println("CYCLES EXERCISE 2: ");

        SwitchTest.select();

        System.out.println("CYCLES EXERCISE 3: ");

        While.whilee();
    }
}