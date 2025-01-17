package com.utp.microservicios.app.notas.services;

import java.util.List;
import java.util.Optional;
import com.utp.microservicios.app.notas.entity.Alumno;

public interface AlumnoService {

	public List<Alumno> findAll();
	
	public Optional<Alumno> findById(int id);
	
	public Alumno save(Alumno alumno);
	
	public void deleteById(int id);
	
	public List<Alumno> obtenerAlumnoPorUsuario(int usuario);
	
	public Alumno actualizarAlumno(int alumnoId, Alumno alumno);
}
