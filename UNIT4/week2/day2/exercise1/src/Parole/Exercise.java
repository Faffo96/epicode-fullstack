package Parole;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Exercise {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many words do you want to insert?:");
        int wordsNumber = scanner.nextInt();
        scanner.nextLine();

        LinkedList<String> words = new LinkedList<>();
        HashSet<String> duplicatedWords = new HashSet<>();

        for (int i = 0; i < wordsNumber; i++) {
            System.out.println("Which word?:");
            String word = scanner.nextLine();

            if (words.contains(word)) {
                duplicatedWords.add(word);
            }
            words.add(word);
        }

        System.out.println("The inserted words are:");
        System.out.println(words);

        System.out.println("The duplicated words are:");
        System.out.println(duplicatedWords);

        words.removeAll(duplicatedWords);
        System.out.println("Distinct words:");
        System.out.println(words);
    }
}

