package com.epicode.events_new.Repository;

import com.epicode.events_new.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findById(int email);

    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.email = :email")
    List<Event> findEventsByUserEmail(@Param("email") String email);
}
