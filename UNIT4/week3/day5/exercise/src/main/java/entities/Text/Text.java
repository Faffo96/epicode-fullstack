package entities.Text;

import Enums.Periodicity;
import Exceptions.ArchiveException;
import ch.qos.logback.core.LogbackException;
import entities.Book.Book;
import entities.Magazine.Magazine;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "texts")
public abstract class Text {
    @Id
    private String ISBNcode;
    private String title;
    @Column(name="publication_date")
    private LocalDate publicationDate;
    @Column(name="page_number")
    private Integer pageNumber;

    protected static Set<String> uniqueISBNs = new HashSet<>();
    static Logger logger = LoggerFactory.getLogger("logger1");

    public Text(String ISBNcode, String title, LocalDate publicationDate, Integer pageNumber) throws IOException {
        if (uniqueISBNs.contains(ISBNcode)) {
            writeInLog("error", "ISBN code in constructor must be unique.");
            throw new IllegalArgumentException("ISBN code in constructor must be unique.");
        }
        this.ISBNcode = ISBNcode;
        this.title = title;
        this.publicationDate = publicationDate;
        this.pageNumber = pageNumber;
        uniqueISBNs.add(ISBNcode);
    }

    public Text() {
    }

    public String getISBNcode() {
        return ISBNcode;
    }

    public void setISBNcode(String ISBNcode) throws ArchiveException {
        // Remove the old ISBN code from the 'uniqueISBNs' set
        uniqueISBNs.remove(this.ISBNcode);
        this.ISBNcode = ISBNcode;

        if (uniqueISBNs.contains(ISBNcode)) {
            writeInLog("error", "ISBN code in setISBNcode() must be unique.");
            throw new ArchiveException("ISBN code in setISBNcode() must be unique.");
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

    @Override
    public String toString() {
        return "Text{" +
                "ISBNcode='" + ISBNcode + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", pageNumber=" + pageNumber;
    }

    /*MY METHODS*/

    public static void removeByISBN(String ISBNcode) throws Exception {

    }

    /*@NotNull
    public static List<Text> findByPublicationDate(LocalDate publicationDate) throws ArchiveException {
        List<Text> foundTexts = archive.stream()
                .filter(text -> text.getPublicationDate().equals(publicationDate))
                .collect(Collectors.toList());

        if (foundTexts.isEmpty()) {
            writeInLog("error", "No texts found for this publication date: " + publicationDate);
            throw new ArchiveException("No texts found for this publication date: " + publicationDate);
        }

        return foundTexts;
    }*/

    /*public Text findByISBN(Session session, String isbnCode) {
        Transaction tx = null;
        Text text = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("FROM Text WHERE ISBNcode = :isbnCode");
            query.setParameter("isbnCode", isbnCode);

            text = (Text) query.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        return text;
    }*/

    @NotNull
    public static void writeInLog(String level, Object obj) throws LogbackException {
        switch (level.toLowerCase()) {
            case "trace":
                logger.trace(obj.toString());
                break;
            case "debug":
                logger.debug(obj.toString());
                break;
            case "info":
                logger.info(obj.toString());
                break;
            case "warn":
                logger.warn(obj.toString());
                break;
            case "error":
                logger.error(obj.toString());
                break;
            default:
                logger.error("Invalid Logback message level. Choose between TRACE, DEBUG, INFO, WARN, ERROR.");
                throw new LogbackException("Invalid Logback message level. Choose between TRACE, DEBUG, INFO, WARN, ERROR.");
        }
    }
}
