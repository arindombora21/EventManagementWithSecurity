

package com.example.demo.controller;

import com.example.demo.model.Participant;
import com.example.demo.service.ParticipantService;
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
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping("/participants")
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantService.createParticipant(participant);
    }

    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Participant participant = participantService.getParticipantById(id);
        return ResponseEntity.ok(participant);
    }

    @PutMapping("/participants/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participantDetails) {
        Participant updatedParticipant = participantService.updateParticipant(id, participantDetails);
        return ResponseEntity.ok(updatedParticipant);
    }

    @DeleteMapping("/participants/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    

}

