package com.example.demo.dao;

import com.example.demo.model.Participant;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    
}
