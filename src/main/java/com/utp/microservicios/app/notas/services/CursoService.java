package com.utp.microservicios.app.notas.services;

import java.util.List;
import java.util.Optional;
import com.utp.microservicios.app.notas.entity.Curso;

public interface CursoService {

	public List<Curso> findAll();
	
	public Optional<Curso> findById(int id);
	
	public Curso save(Curso curso);
	
	public void deleteById(int id);
		
	public Curso actualizarCurso(int cursoId, Curso curso);
}
