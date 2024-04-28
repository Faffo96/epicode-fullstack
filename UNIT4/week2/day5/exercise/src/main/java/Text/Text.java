package Text;

import Enums.Periodicity;
import Exceptions.ArchiveException;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public abstract class Text {
    private String ISBNcode;
    private final String title;
    private LocalDate publicationDate;
    private Integer pageNumber;

    protected static Set<String> uniqueISBNs = new HashSet<>();
    protected static List<Text> archive = new ArrayList<>();
    private final static File file = new File("./persistance/persistentArchive.txt");

    public Text(String ISBNcode, String title, LocalDate publicationDate, Integer pageNumber) throws IOException {
        if (uniqueISBNs.contains(ISBNcode)) {
            throw new IllegalArgumentException("ISBN code must be unique.");
        }
        this.ISBNcode = ISBNcode;
        this.title = title;
        this.publicationDate = publicationDate;
        this.pageNumber = pageNumber;
        uniqueISBNs.add(ISBNcode);
    }

    public String getISBNcode() {
        return ISBNcode;
    }

    public void setISBNcode(String ISBNcode) throws ArchiveException {
        // Remove the old ISBN code from the 'uniqueISBNs' set
        uniqueISBNs.remove(this.ISBNcode);

        if (uniqueISBNs.contains(ISBNcode)) {
            throw new ArchiveException("ISBN code must be unique.");
        }
        this.ISBNcode = ISBNcode;
        // Add the new ISBN code to the 'uniqueISBNs' set
        uniqueISBNs.add(ISBNcode);
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public static List<Text> getArchive() {
        return archive;
    }

    @Override
    public String toString() {
        return "Text{" +
                "ISBNcode='" + ISBNcode + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", pageNumber=" + pageNumber;
    }

    /*MY METHODS*/

    public static void addToArchive(Text text) throws IOException {
        archive.add(text);
        saveArchiveInTxt(archive);
    }

    public static void removeFromArchive(Text text) throws IOException {
        archive.remove(text);
        saveArchiveInTxt(archive);
    }

    @NotNull
    public static Text findByISBN(String ISBNcode) throws ArchiveException {
        Optional<Text> foundText = archive.stream()
                .filter(text -> text.getISBNcode().equals(ISBNcode))
                .findFirst();

        if (foundText.isEmpty()) {
            throw new ArchiveException("No text found with this ISBN: " + ISBNcode);
        }

        return foundText.get();
    }

    public static void removeByISBN(String ISBNcode) throws Exception {
        archive.remove(findByISBN(ISBNcode));
        saveArchiveInTxt(archive);
    }

    @NotNull
    public static List<Text> findByPublicationDate(LocalDate publicationDate) throws ArchiveException {
        List<Text> foundTexts = archive.stream()
                .filter(text -> text.getPublicationDate().equals(publicationDate))
                .collect(Collectors.toList());

        if (foundTexts.isEmpty()) {
            throw new ArchiveException("No texts found for this publication date: " + publicationDate);
        }

        return foundTexts;
    }

    @NotNull
    public static void saveArchiveInTxt(List<Text> archive) throws IOException {
        try {
            String textStr = archive.stream().map(text -> {
                if (text instanceof Book.Book book) {
                    return book.getISBNcode() + "@" + book.getTitle() + "@" + book.getPublicationDate() + "@" + book.getPageNumber() + "@" + book.getAuthor() + "@" + book.getGenre();
                } else if (text instanceof Magazine.Magazine magazine) {
                    return magazine.getISBNcode() + "@" + magazine.getTitle() + "@" + magazine.getPublicationDate() + "@" + magazine.getPageNumber() + "@" + magazine.getPeriodicity();
                } else {
                    return "";
                }
            }).collect(Collectors.joining("#"));
            FileUtils.write(file, textStr, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
        }
    }

    public static void restoreArchive() throws IOException {
        archive.clear();
        try {
            String archiveStringified = FileUtils.readFileToString(file, Charset.defaultCharset());
            String[] textsStringified = archiveStringified.split("#");

            for (String textStringified : textsStringified) {
                String[] textAttributes = textStringified.split("@");

                String ISBNcode = textAttributes[0];
                String title = textAttributes[1];
                LocalDate publicationDate = LocalDate.parse(textAttributes[2]);
                Integer pageNumber = Integer.parseInt(textAttributes[3]);

                if (textAttributes.length == 6) {
                    String author = textAttributes[4];
                    String genre = textAttributes[5];
                    Book.Book book = new Book.Book(ISBNcode, title, publicationDate, pageNumber, author, genre);
                    archive.add(book);
                } else if (textAttributes.length == 5) {
                    String periodicityStr = textAttributes[4];
                    Periodicity periodicity = Periodicity.valueOf(periodicityStr);

                    Magazine.Magazine magazine = new Magazine.Magazine(ISBNcode, title, publicationDate, pageNumber, periodicity);
                    archive.add(magazine);
                } else {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            System.out.println("Input/output error occurred when restoring archive.");
        }
    }

    public static void clearArchiveTxt() {
        FileUtils.deleteQuietly(file);
    }
}
