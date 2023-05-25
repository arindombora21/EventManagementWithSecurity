package com.example.demo.service;

import com.example.demo.dao.ParticipantRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));
    }

    public Participant updateParticipant(Long id, Participant participantDetails) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));

        participant.setFirstName(participantDetails.getFirstName());
        participant.setLastName(participantDetails.getLastName());
        participant.setOrganizationName(participantDetails.getOrganizationName());
        participant.setDesignation(participantDetails.getDesignation());
        participant.setEmailAddress(participantDetails.getEmailAddress());

        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));

        participantRepository.delete(participant);
    }
}
