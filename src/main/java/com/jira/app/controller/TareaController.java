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

import com.jira.app.DAO.TareaDao;
import com.jira.app.entidad.Tareas;
import com.jira.app.servicios.TareaServicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

//	@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class TareaController {
	@Autowired
	private TareaServicio userTarea;

	// crear usuario
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Tareas user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userTarea.save(user));

	}

	// Borrar usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		if (!userTarea.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userTarea.deleteById(id);
		return ResponseEntity.ok().build();

	}

	// Leer usuario
	@GetMapping("/{id}") // se pone asi porque el id puede camnbiar
	public ResponseEntity<?> read(@PathVariable(value = "id") Long id) {
		Optional<Tareas> oUser = userTarea.findById(id);

		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUser);
	}

	@GetMapping("/prueba") // se pone asi porque el id puede camnbiar
	public String prueba() {
		return "prueba";

	}

	@GetMapping
	public List<Tareas> realAll() {
		List<Tareas> users = StreamSupport.stream(userTarea.findAll().spliterator(), false) // recorre como un for los
																							// usuarios
																							// secuencialmente
				.collect(Collectors.toList());// metemos en una lista
		return users;
	}
    @PersistenceContext
    private EntityManager entityManager;
    @GetMapping("/num") 
	public Integer obtenerMaximoMiColumna() {
        String sqlQuery = "SELECT MAX(id) FROM tareas";
        Query query = entityManager.createNativeQuery(sqlQuery);
        Object result = query.getSingleResult();
        
        if (result != null) {
            return (Integer) result;
        } else {
            return 0; // o cualquier valor por defecto en caso de que la tabla esté vacía
        }
	}
	// Leer usuario
	@PutMapping("/{id}") // se pone asi porque el id puede camnbiar
	public ResponseEntity<?> update(@RequestBody Tareas tareaCambios, @PathVariable(value = "id") Long id) {
		Optional<Tareas> oUser = userTarea.findById(id);
		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		oUser.get().setStart(tareaCambios.getStart());
		oUser.get().setEnd(tareaCambios.getEnd());
		oUser.get().setTitle(tareaCambios.getTitle());
		oUser.get().setDescription(tareaCambios.getDescription());
		oUser.get().setAllDay(tareaCambios.isAllDay());
		oUser.get().setFree(tareaCambios.isFree());
		oUser.get().setColor(tareaCambios.getColor());

		return ResponseEntity.status(HttpStatus.CREATED).body(userTarea.save(oUser.get()));

	}
}
