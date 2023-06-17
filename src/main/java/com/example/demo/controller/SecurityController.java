package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Security.JwtTokenHelper;
import com.example.demo.model.JwtAuthRequest;
import com.example.demo.model.JwtAuthResponse;
import com.example.demo.model.Owner;
import com.example.demo.service.OwnerDetailsService;

@RestController
@CrossOrigin(origins = "https://eventmanagementfrontend-production.up.railway.app")
public class SecurityController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private OwnerDetailsService ownerDetailsService;

	
	@PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody JwtAuthRequest request){
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = ownerDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse authResponse = new JwtAuthResponse();
		
		authResponse.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
    }
	
	private void authenticate(String username,String password) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}
	
}

