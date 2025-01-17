package ru.vasilev.securityexample.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.vasilev.securityexample.models.User;
import ru.vasilev.securityexample.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class RegisterController {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		// проверяем, есть ли пользак в базе
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			return ResponseEntity.ok("Username is already taken!");
		}
		// если нет, то хешируем пароль и вносим
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Collections.singleton("ROLE_USER"));
		
		userRepository.save(user);
		return ResponseEntity.ok("User registered successfully");
	}
}