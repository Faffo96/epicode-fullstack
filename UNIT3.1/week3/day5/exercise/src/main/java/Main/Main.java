package Main;

import dao.*;
import entities.Book.Book;
import Enums.Periodicity;
import entities.Magazine.Magazine;
import entities.Text.Text;
import entities.Loan.Loan;
import entities.User.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        BookDao bookDao = new BookDao(em);
        LoanDao loanDao = new LoanDao(em);
        MagazineDao magazineDao = new MagazineDao(em);
        TextDao textDao = new TextDao(em);
        UserDao userDao = new UserDao(em);

        LocalDate specificDate = LocalDate.of(2024, 4, 27);
        LocalDate specificDate2 = LocalDate.of(2023, 6, 21);



        /*Book b1 = new Book("abcd", "Red", specificDate, 19, "Mario", "Horror");
        Book b2 = new Book("abcd2", "Green", specificDate2, 40, "Mario", "Horror");
        Book b3 = new Book("abcd3", "Blue", specificDate, 56, "Pino", "Horror");
        Magazine m1 = new Magazine("abcd4", "Yellow", specificDate2, 119, Periodicity.WEEKLY);
        Magazine m2 = new Magazine("abcd5", "purple", specificDate2, 219, Periodicity.YEARLY);
        Magazine m3 = new Magazine("abcd6", "orange", specificDate2, 319, Periodicity.YEARLY);
        User u1 = new User("Mario", "Rossi", LocalDate.of(1984, 3, 13));
        User u2 = new User("Mario", "Rossi", LocalDate.of(1984, 3, 13));
        User u3 = new User("Mario", "Rossi", LocalDate.of(1984, 3, 13));
        Loan l1 = new Loan(u1, m1);
        Loan l2 = new Loan(u1, b1);
        Loan l3 = new Loan(u2, b2);
        Loan l4 = new Loan(u3, b2);
        l3.setEffectiveReturnDate(LocalDate.of(2024, 7, 30));
        l4.setEffectiveReturnDate(LocalDate.of(2024, 8, 22));
        l1.setLoanStartDate(LocalDate.of(2024, 3, 22));
        l1.setReturnDate(LocalDate.of(2024, 4, 22));
        l3.setLoanStartDate(LocalDate.of(2024, 2, 25));
        l3.setReturnDate(LocalDate.of(2024, 3, 25));
        bookDao.save(b1);
        bookDao.save(b2);
        bookDao.save(b3);
        magazineDao.save(m1);
        magazineDao.save(m2);
        magazineDao.save(m3);
        userDao.save(u1);
        userDao.save(u2);
        userDao.save(u3);
        loanDao.save(l1);
        loanDao.save(l2);
        loanDao.save(l3);
        loanDao.save(l4);*/


        System.out.println("Removing Book b1 from archive by ISBN Code..");

        textDao.deleteByISBN("abcd6");

        System.out.println();

        System.out.println("Search b2 by ISBN code 'abcd2'");

        System.out.println(textDao.findByISBN("abcd2"));

        System.out.println();

        System.out.println("Search by publication date '2023, 6, 21': ");

        for (Text text : textDao.findByPublicationDate(specificDate2)) {
            System.out.println(text);
        }

        System.out.println();

        System.out.println("Search by partial author name 'Pin' instead of 'Pino': ");

        for (Book book : bookDao.findByAuthor("Pin")) {
            System.out.println(book);
        }

        System.out.println();

        System.out.println("Search by partial title 'r': ");

        for (Text text : textDao.findByTitle("r")) {
            System.out.println(text);
        }

        System.out.println("Search Text borrowed to a user's cardCode: ");

        for (Loan loan : loanDao.findLoansByUserCardNumber(35)) {
            System.out.println(loan.getBorrowedElement());
        }

        System.out.println();

        System.out.println("Search for overdue and unpaid loans: ");

        for (Loan loan : loanDao.findExpiredLoans()) {
            System.out.println(loan);
        }
    }
}