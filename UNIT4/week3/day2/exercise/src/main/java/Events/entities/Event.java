package Events.entities;

import Events.enums.EventType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "event")
    private List<Partecipation> partecipations;

    @Column(nullable = false)
    private int partecipantsLimit;

    public Event(String title, LocalDate eventDate, EventType eventType, int partecipantsLimit) {
        this.title = title;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.partecipantsLimit = partecipantsLimit;
    }

    public Event() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getPartecipantsLimit() {
        return partecipantsLimit;
    }

    public void setPartecipantsLimit(int partecipantsLimit) {
        this.partecipantsLimit = partecipantsLimit;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", eventType='" + eventType + '\'' +
                ", partecipantsLimit=" + partecipantsLimit +
                '}';
    }
}
