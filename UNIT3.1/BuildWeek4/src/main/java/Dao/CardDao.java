package Dao;

import Entities.Card;
import Entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CardDao {
    private static EntityManager em;

    public CardDao(EntityManager em) {
        this.em = em;
    }

    public void save(Card card){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(card);
        et.commit();
    }

    public Card getById(int id){
        return em.find(Card.class, id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Card card = getById(id);

        if (card != null){
            em.remove(card);
        } else {
            System.out.println("Tessera non trovata");
        }
        et.commit();
    }

    public void buyCard(User user) {
        Card card = new Card(user);
        save(card);
    }
}
