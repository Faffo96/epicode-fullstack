package dao;

import entities.Text.Text;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class TextDao {
    private EntityManager em;

    public TextDao(EntityManager em) {
        this.em = em;
    }

    public void save(Text text){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(text);
        et.commit();
    }

    public Text getById(int id){
        Text s = em.find(Text.class,id);

        return s;
    }

    public void delete(Text text){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(text);
        et.commit();
    }

    public List<Text> findByPublicationDate(LocalDate publicationDate) {
        Query query = em.createQuery("SELECT t FROM Text t WHERE t.publicationDate = :publicationDate", Text.class);
        query.setParameter("publicationDate", publicationDate);
        return query.getResultList();
    }

    public List<Text> findByTitle(String title) {
        Query query = em.createQuery("SELECT t FROM Text t WHERE t.title LIKE :title", Text.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }


    public Text findByISBN(String isbnCode){
        Query query = em.createQuery("SELECT t FROM Text t WHERE t.ISBNcode = :isbnCode", Text.class);
        query.setParameter("isbnCode", isbnCode);
        try {
            return (Text) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    public void deleteByISBN(String isbnCode) {
        Text textToDelete = findByISBN(isbnCode);

        if (textToDelete != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            try {
                em.remove(textToDelete);

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } else {
            System.out.println("Nessun testo trovato con ISBN code: " + isbnCode);
        }
    }


}
