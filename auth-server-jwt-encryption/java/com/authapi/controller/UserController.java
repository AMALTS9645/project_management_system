package com.authapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authapi.dto.JwtToken;
import com.authapi.dto.UserCredentials;
import com.authapi.model.User;
import com.authapi.service.UserAuthService;
import com.authapi.userrepository.UserRepository;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authMgr;

	@Autowired
	private UserAuthService authService;

//	@GetMapping("/users")
//	public List<User> getAllUsers(){
//		return repo.findAll();
//	}
//
//	@PostMapping("/users")
//	public String addUser(@RequestBody User user) {
//		repo.save(user);
//		return user.getUsername();
//	}
//
	@PostMapping("/login")
	public JwtToken authenticate(@RequestBody UserCredentials user) {
		authMgr.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		String jwt = authService.validateUserCredentials(user);

		return new JwtToken(jwt);
	}

}
