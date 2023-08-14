package com.authapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class HomeController {

	@GetMapping
	public String greet() {
		return "hello from security app";
	}
	
	@GetMapping("/user")
	public String greetUser() {
		return "hello from security app User";
	}
	
	@GetMapping("/admin")
	public String greetAdmin() {
		return "hello from security app Admin";
	}
}
