package Events.dao;

import Events.entities.Event;
import Events.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDao {
    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    public void save(User user){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(user);
        et.commit();
    }

    public User getById(int id){
        User s = em.find(User.class,id);

        return s;
    }

    public void delete(User user){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(user);
        et.commit();
    }
}
