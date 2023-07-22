package com.jira.app.servicios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jira.app.entidad.User;

public interface UserServicio {
	
	public Iterable<User> findAll();

	Page<User> findAll(Pageable pageable);

	public Optional<User> findById(Long id);

	public User save(User user);

	public void deleteById(Long id);

}
