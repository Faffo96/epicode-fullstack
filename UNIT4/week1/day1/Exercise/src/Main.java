

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] words = new String[5];
        words[0] = "Piacere ";
        words[1] = "è ";
        words[2] = "il ";
        words[3] = "mio ";
        words[4] = "nome.";


        System.out.println(Operators.operation(2, 2));

        System.out.println(Operators.concat("ciao,", 4));

        System.out.println(Operators.arrayStringsConcat(words, "Fabio"));

        System.out.println(Operators.concat3("ciao ", "piacere ", "Fabio "));

        System.out.println(Operators.evenOdd(5));

        System.out.println(Operators.triangleArea(5, 5 , 10));

    }
}