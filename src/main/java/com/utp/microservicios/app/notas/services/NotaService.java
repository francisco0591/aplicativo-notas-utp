package com.utp.microservicios.app.notas.services;

import java.util.List;
import java.util.Optional;

import com.utp.microservicios.app.notas.entity.Nota;

public interface NotaService {

	public List<Nota> findAll();
	
	public Optional<Nota> findById(int id);
	
	public Nota save(Nota nota);
	
	public void deleteById(int id);
	
	public List<Nota> findByNotaxAlumnoCurso(int alumno,int curso);
	
	public List<Nota> findByNotaxAlumno(int alumno);

	public int obtenerPromedioNotasAlumno(int alumnoId,int cursoId);

}
