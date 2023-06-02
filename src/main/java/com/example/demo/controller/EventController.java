

package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.Participant;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://eventmanagementfrontend-production.up.railway.app")
@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/location/{location}")
    public List<Event> getEventsByLocation(@PathVariable String location) {
        return eventService.getEventsByLocation(location);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/events/{id}/participants")
    public ResponseEntity<List<Participant>> getEventParticipants(@PathVariable Long id) {
        List<Participant> participants = eventService.getEventParticipants(id);
        return ResponseEntity.ok(participants);
    }

    @PostMapping("/events/{id}/participants")
    public ResponseEntity<Participant> registerParticipant(@PathVariable Long id, @RequestBody Participant participant) {
        Participant savedParticipant = eventService.registerParticipant(id, participant);
        return ResponseEntity.ok(savedParticipant);
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<String> registerParticipantForEvent(@PathVariable("eventId") long eventId, @RequestBody Participant participant) {
        boolean isRegistered = eventService.registerParticipantForEvent(eventId, participant);
        if (isRegistered) {
            return ResponseEntity.ok("Participant registered successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

}

