package com.jira.app.servicios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jira.app.entidad.Tareas;

public interface TareaServicio {
	
	public Iterable<Tareas> findAll();

	Page<Tareas> findAll(Pageable pageable);

	public Optional<Tareas> findById(Long id);

	public Tareas save(Tareas user);

	public void deleteById(Long id);
	

}
