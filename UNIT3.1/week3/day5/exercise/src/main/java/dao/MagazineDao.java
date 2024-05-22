package dao;

import entities.Magazine.Magazine;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MagazineDao {
    private EntityManager em;

    public MagazineDao(EntityManager em) {
        this.em = em;
    }

    public void save(Magazine magazine){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(magazine);
        et.commit();
    }

    public Magazine getById(int id){
        Magazine s = em.find(Magazine.class,id);

        return s;
    }

    public void delete(Magazine magazine){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(magazine);
        et.commit();
    }
}
