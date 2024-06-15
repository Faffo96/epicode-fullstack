package com.epicode.events_new.Controller;

import com.epicode.events_new.Dto.EventDto;
import com.epicode.events_new.Entity.Event;
import com.epicode.events_new.Entity.User;
import com.epicode.events_new.Exception.BadRequestException;
import com.epicode.events_new.Exception.EventNotFoundException;
import com.epicode.events_new.Exception.PartecipantsLimitException;
import com.epicode.events_new.Exception.UserNotFoundException;
import com.epicode.events_new.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public String POSTEvent(@RequestBody @Validated EventDto event, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return eventService.POSTEvent(event);
    }

    @GetMapping("/events")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public Page<Event> GETAllEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return eventService.GETEvents(page, size, sortBy);
    }

    @GetMapping("/events/{id}")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public Event GETEventById(@PathVariable int id) throws EventNotFoundException {
        try {
            return eventService.GETEventById(id);
        } catch (EventNotFoundException e) {
            throw new EventNotFoundException("Event with id: " + id + " not found.");
        }

    }

    @PutMapping(path = "/events/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public @ResponseBody Event PUTEvent(@PathVariable int id, @RequestBody @Validated EventDto event, BindingResult bindingResult) throws BadRequestException, EventNotFoundException, PartecipantsLimitException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        if (event.getUsers().size() > event.getParticipantsLimit()) {
            throw new PartecipantsLimitException("Event with id: " + id + " has reached the maximum partecipants");
        }

        return eventService.PUTEvent(id, event);
    }

    @DeleteMapping("/events/{id}")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public String DELETEEvent(@PathVariable int id) throws EventNotFoundException {
        return eventService.DELETEEvent(id);
    }

    @PatchMapping(path = "/events/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public @ResponseBody Event patchEventUsers(@PathVariable int id, @RequestBody List<String> userEmails) throws EventNotFoundException, PartecipantsLimitException {
        Event event = eventService.GETEventById(id);
        if (event == null) {
            throw new EventNotFoundException("Event with id: " + id + " not found.");
        } else if (event.getUsers().size() >= event.getPartecipantsLimit()) {
            throw new PartecipantsLimitException("Event with id: " + id + " has reached the maximum partecipants");
        }
        return eventService.patchEventUsers(id, userEmails);
    }


    @PatchMapping("/events/{id}/removeUser")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public ResponseEntity<Event> removeUserFromEvent(@PathVariable int id, @RequestParam String userEmail) throws EventNotFoundException, UserNotFoundException {
        Event updatedEvent = eventService.removeUserFromEvent(id, userEmail);
        return ResponseEntity.ok(updatedEvent);
    }

    @GetMapping("/events/user")
    @PreAuthorize("hasAnyAuthority('EVENT_CREATOR', 'USER')")
    public List<Event> getUserEvents(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Rimuovi i primi 7 caratteri ("Bearer ")
            return eventService.getEventsByUser(token);
        } else {
            throw new IllegalArgumentException("Bearer token not found in Authorization header");
        }
    }

    @DeleteMapping("/events/{id}/deleteUserEvent")
    @PreAuthorize("hasAnyAuthority('EVENT_CREATOR', 'USER')")
    public ResponseEntity<String> deleteEventByUserAndEventId(@RequestHeader(name = "Authorization") String authorizationHeader, @PathVariable int id) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                eventService.deleteEventByUserEmailAndEventId(token, (int) id);
                return ResponseEntity.ok("Evento eliminato correttamente per l'utente autenticato.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'eliminazione dell'evento: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Bearer token non trovato nell'header Authorization");
        }
    }
}
