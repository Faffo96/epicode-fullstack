package RandomNumbers;

import java.util.*;

public class RandomNumbers {
    public static List<Integer> generateRandomNumbers(int randomQuantity) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < randomQuantity; i++) {
            int randomNumber = random.nextInt(101);
            randomNumbers.add(randomNumber);
        }

        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    public static List<Integer> invertedRandomNumbers(List<Integer> array) {
        List<Integer> invertedArray = new ArrayList<>(array);
        Collections.reverse(invertedArray);
        invertedArray.addAll(array);
        return invertedArray;
    }

    public static List<Integer> evenOdd(List<Integer> array, boolean bool) {
        List<Integer> result = new ArrayList<>();
        if (bool) {
            for (int num : array) {
                if (num % 2 == 0 && num != 0) {
                    result.add(num);
                }
            }
        } else {
            for (int num : array) {
                if (num % 2 != 0) {
                    result.add(num);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("How much random numbers do you want generate?");

        int randomQuantity = scanner.nextInt();

        scanner.close();

        System.out.println("Random generated numbers: " + generateRandomNumbers(randomQuantity));

        System.out.println();

        System.out.println("Random generated numbers: " + invertedRandomNumbers(generateRandomNumbers(randomQuantity)));

        System.out.println();

        System.out.println("Random even generated numbers: " + evenOdd(generateRandomNumbers(randomQuantity), true));

        System.out.println();

        System.out.println("Random odd generated numbers: " + evenOdd(generateRandomNumbers(randomQuantity), false));
    }
}
