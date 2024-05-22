package dao;

import entities.Loan.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class LoanDao {
    private EntityManager em;

    public LoanDao(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(loan);
        et.commit();
    }

    public Loan getById(int id){
        Loan s = em.find(Loan.class,id);

        return s;
    }

    public void delete(Loan loan){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(loan);
        et.commit();
    }

    public List<Loan> findLoansByUserCardNumber(int userCardNumber) {
        Query query = em.createQuery("SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber", Loan.class);
        query.setParameter("cardNumber", userCardNumber);
        return query.getResultList();
    }

    public List<Loan> findExpiredLoans() {
        Query query = em.createQuery("SELECT l FROM Loan l WHERE l.returnDate < CURRENT_DATE AND l.effectiveReturnDate IS NULL", Loan.class);
        return query.getResultList();
    }
}
