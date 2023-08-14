package com.authapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authapi.dto.UserCredentials;
import com.authapi.exception.InvalidCredentialsException;
import com.authapi.model.User;
import com.authapi.userrepository.UserRepository;
import com.authapi.util.JwtUtil;


@Service
public class UserAuthService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private JwtUtil jwtUtil;

	public String validateUserCredentials(UserCredentials user) {
		String username = user.getUsername();
		String password = user.getPassword();

		User usr = repo.findByUsername(username);

		if (usr == null)
			throw new InvalidCredentialsException("Invalid username or password");

		String pass = usr.getPassword();
		if (!pass.equals(password)) {

			throw new InvalidCredentialsException("Invalid username or password");

		}

		String jwt = jwtUtil.generateJwt(username);
		String jwt = jwtUtil.generateToken(username);
		return jwt;

	}

}

