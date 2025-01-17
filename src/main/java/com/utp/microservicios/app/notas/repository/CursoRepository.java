package com.utp.microservicios.app.notas.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.utp.microservicios.app.notas.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso,Integer>{

	List<Curso> findAll();

}
