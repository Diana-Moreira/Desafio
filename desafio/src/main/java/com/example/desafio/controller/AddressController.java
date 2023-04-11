package com.example.desafio.controller;

import com.example.desafio.entities.AddressEntity;
import com.example.desafio.entities.UserEntity;
import com.example.desafio.service.AddressService;
import com.example.desafio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("endereco")
public class AddressController {

	private final AddressService addressService;
	private final UserService userService;

	@GetMapping("{id}")
	public ResponseEntity<AddressEntity> getAddress(@PathVariable Long id) {
		Optional<AddressEntity> objAddress = addressService.findById(id);
		return objAddress
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("{idUser}")
	public ResponseEntity<AddressEntity> createAddress(@PathVariable Long idUser, @RequestBody AddressEntity addressObj) {
		Optional<UserEntity> objUser = userService.findById(idUser);

		return objUser.map(userEntity -> {
			userEntity.getListaEndereco().add(addressObj);
			userService.save(userEntity);
			return ResponseEntity.status(201).body(addressObj);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("{idUsuario}/{idEndereco}")
	public ResponseEntity<AddressEntity> updateUserAddressByAddressId(@PathVariable Long idUsuario, @PathVariable Long idEndereco, @RequestBody AddressEntity addressObj) {
		Optional<UserEntity> objUser = userService.findById(idUsuario);
		Optional<AddressEntity> objAddress = addressService.findById(idEndereco);

		if (objUser.isEmpty() || objAddress.isEmpty())
			return ResponseEntity.notFound().build();

		return objAddress.map(addressEntity -> {
			addressObj.setIdEndereco(idEndereco);
			addressService.updateAddressByEntity(addressObj);
			return ResponseEntity.status(204).body(addressObj);
		}).orElseGet(() -> ResponseEntity.status(404).build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<UserEntity> deleteAddress(@PathVariable Long id) {
		addressService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}