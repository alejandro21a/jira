package com.jira.app.servicios;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jira.app.DAO.TareaDao;

import com.jira.app.entidad.Tareas;

import jakarta.transaction.Transactional;


@Service
public class TareaServicioImpl implements TareaServicio {

	@Autowired
	private TareaDao tareaRepositoty;

	@Override
	public Iterable<Tareas> findAll() {
		return tareaRepositoty.findAll();
	}

	@Override
	public Page<Tareas> findAll(Pageable pageable) {
		return tareaRepositoty.findAll(pageable);
	}

	@Override
	public Optional<Tareas> findById(Long id) {
		// TODO Auto-generated method stub
		return tareaRepositoty.findById(id);
	}

	@Override
	@Transactional

	public Tareas save(Tareas tarea) {
		// TODO Auto-generated method stub
		return tareaRepositoty.save(tarea);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		tareaRepositoty.deleteById(id);
	}

}
