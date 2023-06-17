package com.example.demo.service;

import com.example.demo.Security.JwtTokenHelper;
import com.example.demo.dao.EventRepository;
import com.example.demo.dao.OwnerRepository;
import com.example.demo.dao.ParticipantRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Event;
import com.example.demo.model.Owner;
import com.example.demo.model.Participant;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final OwnerRepository ownerRepository;
    private final JwtTokenHelper jwtTokenHelper;

    @Autowired
    public EventService(EventRepository eventRepository, ParticipantRepository participantRepository,OwnerRepository ownerRepository,JwtTokenHelper jwtTokenHelper) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
        this.ownerRepository = ownerRepository;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    public Event createEvent(Event event,HttpServletRequest request) {
    	String username = this.jwtTokenHelper.getUsernameFromToken(request.getHeader("Authorization").substring(7));
		Owner owner= this.ownerRepository.findByUsername(username);
		Set<Event> temp= owner.getEvents();
		temp.add(event);
		owner.setEvents(temp);
		this.ownerRepository.save(owner);
		event.setOwner(owner);
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    public List<Event> getEventByOwner(long id){
    	return eventRepository.findByOwner_id(id);
    }
    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        event.setName(eventDetails.getName());
        event.setDescription(eventDetails.getDescription());
        event.setEmailId(eventDetails.getEmailId());
        event.setStartdate(eventDetails.getStartdate());
        event.setEnddate(eventDetails.getEnddate());
        event.setLocation(eventDetails.getLocation());

        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        eventRepository.delete(event);
    }

    public List<Participant> getEventParticipants(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return event.getParticipants();
    }

    public Participant registerParticipant(Long eventId, Participant participant) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        participant.setEvent(event);
        return participantRepository.save(participant);
    }

    public boolean registerParticipantForEvent(long eventId, Participant participant) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.registerParticipant(participant);
            eventRepository.save(event);
            return true;
        }
        return false;
    }
}