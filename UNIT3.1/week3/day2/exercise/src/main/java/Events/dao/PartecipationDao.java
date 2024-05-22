package Events.dao;

import Events.entities.Event;
import Events.entities.Partecipation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PartecipationDao {
    private EntityManager em;

    public PartecipationDao(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipation partecipation){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(partecipation);
        et.commit();
    }

    public Partecipation getById(int id){
        Partecipation s = em.find(Partecipation.class,id);

        return s;
    }

    public void delete(Partecipation partecipation){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(partecipation);
        et.commit();
    }
}
