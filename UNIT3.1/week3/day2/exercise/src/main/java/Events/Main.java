package Events;

import Events.dao.EventDao;
import Events.dao.LocationDao;
import Events.dao.PartecipationDao;
import Events.dao.UserDao;
import Events.entities.Event;
import Events.entities.Location;
import Events.entities.Partecipation;
import Events.entities.User;
import Events.enums.EventType;
import Events.enums.Gender;
import Events.enums.PartecipationState;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("events");
        EntityManager em = emf.createEntityManager();
        LocationDao locationDao = new LocationDao(em);
        EventDao eventDao = new EventDao(em);
        UserDao userDao = new UserDao(em);
        PartecipationDao partecipationDao = new PartecipationDao(em);


        Event e1 = new Event("evento1", LocalDate.of(2024, 12, 1), EventType.PUBLIC, 1000);
        eventDao.save(e1);

        Location l1 = new Location("Circo Massimo", "Roma");
        locationDao.save(l1);

        User u1 = new User("Mario", "Rossi", "mario@rossi.com", LocalDate.of(1996, 3, 12), Gender.M);
        userDao.save(u1);

        List<Partecipation> partecipations = new ArrayList<>();
        Partecipation p1 = new Partecipation(PartecipationState.CONFIRMED);
        Partecipation p2 = new Partecipation(PartecipationState.NOTCONFIRMED);
        partecipationDao.save(p1);
        partecipationDao.save(p2);
        partecipations.add(p1);
        partecipations.add(p2);

        e1.setLocation(l1);

        p1.setEvent(e1);
        p1.setUser(u1);
        p2.setEvent(e1);
        p2.setUser(u1);

        u1.setUserPartecipations(partecipations);

        partecipationDao.save(p1);
        partecipationDao.save(p2);
        eventDao.save(e1);
        userDao.save(u1);

        System.out.println(e1);

    }
}
