package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Participant;
import com.example.demo.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    // Create a participant
    @PostMapping("/participants")
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantRepository.save(participant);
    }

    // Retrieve all participants
    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    // Retrieve details of a participant by ID
    @GetMapping("/participants/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));
        return ResponseEntity.ok(participant);
    }

    // Update a participant
    @PutMapping("/participants/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participantDetails) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));

        participant.setFirstName(participantDetails.getFirstName());
        participant.setLastName(participantDetails.getLastName());
        participant.setOrganizationName(participantDetails.getOrganizationName());
        participant.setDesignation(participantDetails.getDesignation());
        participant.setEmailAddress(participantDetails.getEmailAddress());

        Participant updatedParticipant = participantRepository.save(participant);
        return ResponseEntity.ok(updatedParticipant);
    }

    // Delete a participant
    @DeleteMapping("/participants/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteParticipant(@PathVariable Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));

        participantRepository.delete(participant);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
