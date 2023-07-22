package com.jira.app.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.app.entidad.Tareas;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface TareaDao extends JpaRepository<Tareas, Long>{

	@Query(value = "SELECT MAX(id) FROM tareas", nativeQuery = true)
    Integer findMaxMiColumna();

}
