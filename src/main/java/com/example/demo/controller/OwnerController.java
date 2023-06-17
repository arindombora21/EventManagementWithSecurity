package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Event;
import com.example.demo.model.Owner;
import com.example.demo.service.OwnerServices;


@RestController
public class OwnerController {
	@Autowired
	private OwnerServices ownerServices;
	

	@GetMapping("/owner")
	public List<Owner> getAllOwners() throws ParseException{
		return this.ownerServices.getAllOwners();
	}
    @PostMapping(value = "/owner")
	public Owner addOwner(@RequestBody Owner owner) throws ParseException{
		return this.ownerServices.addOwner(owner);
	}
	@PutMapping("/owner/{id}")
	public Owner updateOwner(@RequestBody Owner owner) {
		return this.ownerServices.updateOwner(owner);
	}
}

