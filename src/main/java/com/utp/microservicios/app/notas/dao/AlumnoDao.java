package com.utp.microservicios.app.notas.dao;

import java.util.List;
import java.util.Optional;

import com.utp.microservicios.app.notas.entity.Alumno;

public interface AlumnoDao {
	public List<Alumno> findAll();

	public Optional<Alumno> findById(int id);

	public Alumno save(Alumno nota);

	public void deleteById(int id);

	public List<Alumno> findAlumnoUsuario(int usuario);
	
}
