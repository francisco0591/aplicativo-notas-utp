package com.utp.microservicios.app.notas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.utp.microservicios.app.notas.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno,Integer>{

	List<Alumno> findAll();
	
	@Query ("select u from Alumno u where u.usuario.id=?1")
	List<Alumno> findByUsuario(int usuario);
}
