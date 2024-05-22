
public class Operators {

public static int operation(int x, int y){
            return x * y;
        }

public static String concat(String a, int b) {
    return a + b;
}

public static String[] arrayStringsConcat(String[] array, String string) {
    String[] newWords = new String[6];
    newWords[0] = array[0];
    newWords[1] = array[1];
    newWords[2] = array[2];
    newWords[3] = string;
    newWords[4] = array[3];
    newWords[5] = array[4];

    return newWords;
}

public static String concat3(String a, String b, String c) {
    return a + b + c + " " + c + b + a;
}

public static int perimeter(int sideA, int sideB) {
    return sideA * sideB;
}

public static int evenOdd (int num) {
    if (num%2 == 0) {
        return 0;
    } else {
        return 1;
    }
}

public static double triangleArea(int sideA, int sideB, int sideC) {
    double semiPerimeter = (sideA + sideB + sideC) / 2.0;
    double areaSquared = semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC);
    return Math.sqrt(areaSquared);
}
}