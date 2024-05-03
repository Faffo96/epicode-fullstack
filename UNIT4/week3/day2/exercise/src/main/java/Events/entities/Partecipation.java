package Events.entities;

import Events.enums.PartecipationState;

import javax.persistence.*;

@Entity
@Table(name = "partecipations")
public class Partecipation {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @Enumerated(EnumType.STRING)
    private PartecipationState state;

    public Partecipation(PartecipationState state) {
        this.user = user;
        this.event = event;
        this.state = state;
    }

    public Partecipation() {
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public PartecipationState getState() {
        return state;
    }

    public void setState(PartecipationState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Partecipation{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                ", state=" + state +
                '}';
    }
}
