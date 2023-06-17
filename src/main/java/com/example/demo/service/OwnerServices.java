package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OwnerRepository;
import com.example.demo.model.Owner;

@Service
public class OwnerServices {
	@Autowired
	private OwnerRepository ownerRepository;
	
	public List<Owner> getAllOwners(){
		return (List<Owner>) this.ownerRepository.findAll();
	}
	public Owner addOwner(Owner owner) {
		Owner temp=this.ownerRepository.findByUsername(owner.getUsername());
		if(temp==null) {
			Owner result=this.ownerRepository.save(owner);
			return result;
		}
		return temp;
	}
	public Owner updateOwner(Owner owner) {
		Owner temp=this.ownerRepository.findByUsername(owner.getUsername());
		if(temp!=null) {
		    return this.ownerRepository.save(owner);
		}
		return temp;
	}
}

