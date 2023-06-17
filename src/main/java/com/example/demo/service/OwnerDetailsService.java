package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OwnerRepository;
import com.example.demo.model.CustomOwnerDetails;
import com.example.demo.model.Owner;

@Service
public class OwnerDetailsService implements UserDetailsService{

	@Autowired
	private OwnerRepository ownerRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Owner owner = this.ownerRepository.findByUsername(username);
		if(owner==null) {
			throw new UsernameNotFoundException("NO");
		}
		return new CustomOwnerDetails(owner);
	}

}
