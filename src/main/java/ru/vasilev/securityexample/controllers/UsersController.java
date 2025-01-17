package ru.vasilev.securityexample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.vasilev.securityexample.models.User;
import ru.vasilev.securityexample.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UsersController {
	private final UserRepository userRepository;

	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(userRepository.findAll());
	}
}