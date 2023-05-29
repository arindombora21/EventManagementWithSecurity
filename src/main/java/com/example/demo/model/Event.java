package com.example.demo.model;



import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="events")
public class Event{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Name")
	private String name;
	@Column(name="Description")
	private String description;
	@Column(name="Start_Date")
	private String startdate;
	@Column(name="End_Date")
	private String enddate;
	@Column(name="Location")
	private String location;
	@Column(name="Email_Id")
	private String emailId;
	@Column(name="Status")
	private String status;
   
	
	public Event() {
		
	}
	
	public Event(String name, String description, String startdate, String enddate, String location, String emailId, String status) {
		super();
		this.name = name;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
		this.location = location;
		this.emailId = emailId;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("event")
    private List<Participant> participants;

    // Getters and setters

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
    
    public void registerParticipant(Participant participant) {
        participants.add(participant);
        participant.setEvent(this);
    }

	
}








