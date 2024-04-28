package org.example;
import Book.Book;
import Text.Text;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static <istanceof> void main(String[] args) throws Exception {
        Text.restoreArchive();
        List<Text> archive = Text.getArchive();

        LocalDate dataSpecificata = LocalDate.of(2024, 4, 27);
        LocalDate dataSpecificata2 = LocalDate.of(2023, 6, 21);

        /*Book b1 = new Book("abcd", "Red", dataSpecificata, 19, "Mario", "Horror");
        Book b2 = new Book("abcd2", "Green", dataSpecificata2, 40, "Mario", "Horror");
        Book b3 = new Book("abcd3", "Blue", dataSpecificata, 56, "Pino", "Horror");
        Magazine m1 = new Magazine("abcd4", "Yellow", dataSpecificata2, 119, Periodicity.WEEKLY);

        Text.addToArchive(b1);
        Text.addToArchive(b2);
        Text.addToArchive(b3);
        Text.addToArchive(m1);*/

        for (Text item : archive) {
            System.out.println(item);
        }

        /*System.out.println("Removing Book b1 from archive by ISBN Code..");

        Text.removeByISBN("abcd");*/

        System.out.println();

        System.out.println("Search by publication date '2023, 6, 21':");

        for (Text text : Text.findByPublicationDate(dataSpecificata2)) {
            System.out.println(text);
        }

        System.out.println();

        System.out.println("Search by author 'Mario':");

        System.out.println(Book.findByAuthor("Mario"));

        Text.saveArchiveInTxt(archive);
    }
}