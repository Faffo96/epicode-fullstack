package dao;

import entities.Book.Book;
import entities.Text.Text;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class BookDao {
    private EntityManager em;

    public BookDao(EntityManager em) {
        this.em = em;
    }

    public void save(Book book){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(book);
        et.commit();
    }

    public Book getById(int id){
        Book s = em.find(Book.class,id);

        return s;
    }

    public void delete(Book book){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(book);
        et.commit();
    }

    public List<Book> findByAuthor(String author) {
        Query query = em.createQuery("SELECT t FROM Book t WHERE t.author LIKE :author", Book.class);
        query.setParameter("author", "%" + author + "%");
        return query.getResultList();
    }

}
