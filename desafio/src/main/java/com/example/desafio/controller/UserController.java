package com.example.desafio.controller;

import com.example.desafio.entities.AddressEntity;
import com.example.desafio.entities.UserEntity;
import com.example.desafio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuario")
public class UserController {

	private final UserService userService;

	@GetMapping("{id}")
	public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
		Optional<UserEntity> objUser = userService.findById(id);
		return objUser
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		List<UserEntity> allUsers = userService.findAll();
		return ResponseEntity.ok(allUsers);
	}

	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userObj) {
		userService.validationForCriation(userObj);
		return ResponseEntity.status(201).body(userObj);
	}

	@PutMapping("{id}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userObj) {
		Optional<UserEntity> objUser = userService.findById(id);

		return objUser.map(userEntity -> {
			userObj.setIdUsuario(id);
			userService.updateUserByEntity(userObj);
			return ResponseEntity.status(204).body(userObj);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		Optional<UserEntity> objUser = userService.findById(id);

		return objUser.map(userEntity -> {
			userService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

}