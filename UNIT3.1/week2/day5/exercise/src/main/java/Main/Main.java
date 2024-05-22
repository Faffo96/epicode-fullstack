package Main;
import Book.Book;
import Enums.Periodicity;
import Magazine.Magazine;
import Text.Text;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Text.restoreArchive();
        List<Text> archive = Text.getArchive();

        LocalDate specificDate = LocalDate.of(2024, 4, 27);
        LocalDate specificDate2 = LocalDate.of(2023, 6, 21);

        /*Book b1 = new Book("abcd", "Red", specificDate, 19, "Mario", "Horror");
        Book b2 = new Book("abcd2", "Green", specificDate2, 40, "Mario", "Horror");
        Book b3 = new Book("abcd3", "Blue", specificDate, 56, "Pino", "Horror");
        Magazine m1 = new Magazine("abcd4", "Yellow", specificDate2, 119, Periodicity.WEEKLY);
        Text.addToArchive(b1);
        Text.addToArchive(b2);
        Text.addToArchive(b3);
        Text.addToArchive(m1);*/

        /*Magazine m2 = new Magazine("abcd5", "purple", specificDate2, 219, Periodicity.YEARLY);*/
        Magazine m3 = new Magazine("abcd6", "orange", specificDate2, 319, Periodicity.YEARLY);
        Text.addToArchive(m3);

        for (Text item : archive) {
            System.out.println(item);
        }

        /*System.out.println("Removing Book b1 from archive by ISBN Code..");

        Text.removeByISBN("abcd");*/

        System.out.println();

        System.out.println("Search b2 by ISBN code 'abcd2'");

        System.out.println(Text.findByISBN("abcd5"));

        System.out.println();

        System.out.println("Search by publication date '2023, 6, 21':");

        for (Text text : Text.findByPublicationDate(specificDate2)) {
            System.out.println(text);
        }

        System.out.println();

        System.out.println("Search by author 'Pin':");

        for (Book book : Book.findByAuthor("Pin")) {
            System.out.println(book);
        }

        Text.saveArchiveInTxt(archive);
    }
}