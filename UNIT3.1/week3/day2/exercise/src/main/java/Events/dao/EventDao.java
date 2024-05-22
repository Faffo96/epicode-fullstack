package Events.dao;

import Events.entities.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EventDao {
    private EntityManager em;

    public EventDao(EntityManager em) {
        this.em = em;
    }

    public void save(Event event){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(event);
        et.commit();
    }

    public Event getById(int id){
        Event s = em.find(Event.class,id);

        return s;
    }

    public void delete(Event event){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(event);
        et.commit();
    }
}
