package Book;

import Exceptions.ArchiveException;
import Text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Book extends Text {
    private String author;
    private String genre;

    private static List<Text> archive = Text.getArchive();

    public Book(String ISBNcode, String title, LocalDate publicationDate, Integer pageNumber, String author, String genre) throws IOException {
        super(ISBNcode, title, publicationDate, pageNumber);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    @NotNull
    public static List<Book> findByAuthor(String author) throws ArchiveException {
        List<Book> foundBooks = archive.stream()
                .filter(text -> text instanceof Book)
                .map(text -> (Book) text)
                .filter(text -> text.getAuthor().equals(author))
                .collect(Collectors.toList());

        if (foundBooks.isEmpty()) {
            throw new ArchiveException("Nessun libro trovato per questo autore: " + author);
        }

        return foundBooks;
    }
}
