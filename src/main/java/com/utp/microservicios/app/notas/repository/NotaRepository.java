package com.utp.microservicios.app.notas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.utp.microservicios.app.notas.entity.Nota;

public interface NotaRepository extends CrudRepository<Nota, Integer> {

	List<Nota> findAll();

	@Query("select u from Nota u where u.alumno.id=?1")
	List<Nota> findByNotaxAlumno(int alumno);

	@Query("select u from Nota u where u.alumno.id=?1 and u.curso.id=?2")
	List<Nota> findByNotaxAlumnoCurso(int alumno, int curso);
}
