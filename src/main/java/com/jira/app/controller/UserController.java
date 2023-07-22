package com.jira.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.app.entidad.User;
import com.jira.app.servicios.UserServicio;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserServicio userServicio;

	// crear usuario

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userServicio.save(user));

	}

	// Borrar usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		if (!userServicio.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userServicio.deleteById(id);
		return ResponseEntity.ok().build();

	}

	// Leer usuario
	@GetMapping("/{id}") // se pone asi porque el id puede camnbiar
	public ResponseEntity<?> read(@PathVariable(value = "id") Long id) {
		Optional<User> oUser = userServicio.findById(id);

		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUser);
	}

	@GetMapping
	public List<User> realAll() {
		List<User> users = StreamSupport.stream(userServicio.findAll().spliterator(), false) // recorre como un for los
																								// usuarios
																								// secuencialmente
				.collect(Collectors.toList());// metemos en una lista
		return users;
	}

	// Leer usuario
	@PutMapping("/{id}") // se pone asi porque el id puede camnbiar
	public ResponseEntity<?> update(@RequestBody User userCambios, @PathVariable(value = "id") Long id) {
		Optional<User> oUser = userServicio.findById(id);
		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		oUser.get().setNombre(userCambios.getNombre());
		oUser.get().setEmail(userCambios.getEmail());
		oUser.get().setApellidos(userCambios.getApellidos());

		return ResponseEntity.status(HttpStatus.CREATED).body(userServicio.save(oUser.get()));
		
	}
}
