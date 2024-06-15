package com.epicode.events_new.Service;

import com.cloudinary.Cloudinary;
import com.epicode.events_new.Dto.EventDto;
import com.epicode.events_new.Entity.Event;
import com.epicode.events_new.Entity.User;
import com.epicode.events_new.Exception.EventNotFoundException;
import com.epicode.events_new.Exception.UserNotFoundException;
import com.epicode.events_new.Repository.EventRepository;
import com.epicode.events_new.Security.JwtTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private static Logger loggerError = LoggerFactory.getLogger("loggerError");
    private static Logger loggerTrace = LoggerFactory.getLogger("loggerTrace");
    private static Logger loggerWarn = LoggerFactory.getLogger("loggerWarn");

    public String POSTEvent(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setDate(eventDto.getDate());
        event.setLocation(eventDto.getLocation());
        event.setPartecipantsLimit(eventDto.getParticipantsLimit());

        // Ottieni gli utenti partecipanti all'evento
        List<User> participants = new ArrayList<>();
        System.out.println(eventDto.getUsers());
        for (String userEmail : eventDto.getUsers()) {
            try {
                User user = userService.getUserByEmail(userEmail);
                participants.add(user);
            } catch (UserNotFoundException e) {
                loggerWarn.warn("User with email " + userEmail + " not found. Skipping...");
                // Puoi gestire questa situazione come preferisci, ad esempio ignorando l'utente mancante o gestendo l'errore in altro modo
            }
        }

        event.setUsers(participants); // Imposta la collezione di partecipanti

        eventRepository.save(event);
        loggerTrace.trace("Event with id " + event.getId() + " saved.");
        return "Event with id " + event.getId() + " saved.";
    }


    public Page<Event> GETEvents(int page, int size, String sortBy) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return eventRepository.findAll(pageable);
        }

        public Event GETEventById(int id) throws EventNotFoundException {
            Optional<Event> eventOpt = eventRepository.findById(id);

            if (eventOpt.isPresent()) {
                return eventOpt.get();
            } else {
                loggerError.error("Event id: " + id + " not found.");
                throw new EventNotFoundException("Event id: " + id + " not found.");
            }
        }

        public Event PUTEvent(int id, EventDto eventDto) throws EventNotFoundException {
            Event event = GETEventById(id);

            if (event != null) {
                event.setName(eventDto.getName());
                event.setDescription(eventDto.getDescription());
                event.setDate(eventDto.getDate());
                event.setLocation(eventDto.getLocation());
                event.setPartecipantsLimit(eventDto.getParticipantsLimit());

                // Ottieni gli utenti partecipanti all'evento
                List<User> participants = new ArrayList<>();
                System.out.println(eventDto.getUsers());
                for (String userEmail : eventDto.getUsers()) {
                    try {
                        User user = userService.getUserByEmail(userEmail);
                        participants.add(user);
                    } catch (UserNotFoundException e) {
                        loggerWarn.warn("User with email " + userEmail + " not found. Skipping...");
                        // Puoi gestire questa situazione come preferisci, ad esempio ignorando l'utente mancante o gestendo l'errore in altro modo
                    }
                }

                event.setUsers(participants); // Imposta la collezione di partecipanti

                eventRepository.save(event);
                loggerTrace.trace("Event with id " + event.getId() + " modified.");
                return event;
            } else {
                loggerError.error("Event id: " + id + " not found.");
                throw new EventNotFoundException("Event with id " + id + " not found.");
            }
        }

        public String DELETEEvent(int id) throws EventNotFoundException {
            Event event = GETEventById(id);

            if (event != null) {
                eventRepository.delete(event);
                loggerTrace.trace("Event with id " + id + " deleted successfully.");
                return "Event with id " + id + " deleted successfully.";
            } else {
                loggerError.error("Event id: " + id + " not found.");
                throw new EventNotFoundException("Event with id " + id + " not found.");
            }
        }

    public Event patchEventUsers(int id, List<String> userEmails) throws EventNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> {
                    loggerError.error("Event id:" + id + " not found.");
                    return new EventNotFoundException("Event id:" + id + " not found.");
                });

        // Ottieni gli utenti da aggiungere o rimuovere dall'evento
        List<User> usersToAdd = new ArrayList<>();
        for (String userEmail : userEmails) {
            try {
                User user = userService.getUserByEmail(userEmail);
                usersToAdd.add(user);
            } catch (UserNotFoundException e) {
                loggerWarn.warn("User with email " + userEmail + " not found. Skipping...");
                // Puoi gestire questa situazione come preferisci, ad esempio ignorando l'utente mancante o gestendo l'errore in altro modo
            }
        }

        // Aggiungi gli utenti all'evento
        event.getUsers().addAll(usersToAdd);

        // Salva l'evento aggiornato
        Event updatedEvent = eventRepository.save(event);
        loggerTrace.trace("Users added to Event with id " + event.getId());

        return updatedEvent;
    }



    public Event removeUserFromEvent(int id, String userEmail) throws EventNotFoundException, UserNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> {
                    loggerError.error("Event id:" + id + " not found.");
                    return new EventNotFoundException("Event id:" + id + " not found.");
                });

        // Cerca l'utente nella lista degli utenti dell'evento
        Optional<User> userToRemove = event.getUsers().stream()
                .filter(user -> Objects.equals(user.getEmail(), userEmail))
                .findFirst();

        if (userToRemove.isPresent()) {
            // Rimuovi l'utente dalla lista degli utenti dell'evento
            event.getUsers().remove(userToRemove.get());

            loggerTrace.trace("User with id " + userEmail + " removed from Event with id " + id);
            eventRepository.save(event); // Salva le modifiche nell'evento nel repository

            return event;
        } else {
            loggerError.error("User with id " + userEmail + " not found in Event with id " + id);
            throw new UserNotFoundException("User with id " + userEmail + " not found in Event with id " + id);
        }
    }



    private void sendNewEventMail(User user, Event event) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("You've been assigned to a new Event!");
            message.setText("Hi " + user.getName() + ", " +
                    "\n\nYou've been assigned to a new Event!" +
                    "\n It is " + event.getName() + " at " + event.getLocation() + " on " + event.getDate() + ". Enjoy it!");

            javaMailSender.send(message);
        }

    @Transactional(readOnly = true)
    public List<Event> getEventsByUser(String token) {
        String currentUserEmail = jwtTool.getEmailFromToken(token);

        return eventRepository.findEventsByUserEmail(currentUserEmail);
    }

    public void deleteEventByUserEmailAndEventId(String token, int eventId) {
        String currentUserEmail = jwtTool.getEmailFromToken(token);

        Event eventToDelete = eventRepository.findById(eventId)
                .filter(event -> event.getUsers().stream().anyMatch(user -> user.getEmail().equals(currentUserEmail)))
                .orElse(null);

        if (eventToDelete != null) {
            eventRepository.delete(eventToDelete);
            loggerTrace.trace("Event with id " + eventId + " deleted for user with email " + currentUserEmail);
        } else {
            loggerError.error("Event with id " + eventId + " not found for user with email " + currentUserEmail);
            throw new IllegalArgumentException("Event with id " + eventId + " not found for user with email " + currentUserEmail);
        }
    }


    // Metodo di utilità per convertire Event a EventDto
    /*private EventDto convertToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setDate(event.getDate());
        eventDto.setLocation(event.getLocation());
        eventDto.setUsers(event.getUsers().stream().map(User::getEmail).collect(Collectors.toList()));
        eventDto.setParticipantsLimit(event.getPartecipantsLimit());
        return eventDto;
    }*/
    }

