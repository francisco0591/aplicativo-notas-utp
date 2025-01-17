package com.utp.microservicios.app.notas.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utp.microservicios.app.notas.dao.AlumnoDao;
import com.utp.microservicios.app.notas.entity.Alumno;
import com.utp.microservicios.app.notas.services.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	private AlumnoDao alumnoDao;

	@Override
	public List<Alumno> findAll() {
		return this.alumnoDao.findAll();
	}

	@Override
	public Optional<Alumno> findById(int id) {
		return this.alumnoDao.findById(id);
	}

	@Override
	public Alumno save(Alumno alumno) {
		return this.alumnoDao.save(alumno);
	}

	@Override
	public void deleteById(int id) {
		this.alumnoDao.deleteById(id);
	}

	@Override
	public List<Alumno> obtenerAlumnoPorUsuario(int usuario) {
		return this.alumnoDao.findAlumnoUsuario(usuario);
	}

	@Override
	public Alumno actualizarAlumno(int alumnoId, Alumno alumno) {
		 Alumno alumnoBuscado = findById(alumnoId)
	                .orElseThrow(() -> new RuntimeException("Alumno no encontrado!"));

	        // Actualizamos el valor de la nota
		 alumnoBuscado.setNombre(alumno.getNombre());
		 alumnoBuscado.setApellido(alumno.getApellido());
		 alumnoBuscado.setDni(alumno.getDni());
		 alumnoBuscado.setEmail(alumno.getEmail());

	        // Guardamos la nota actualizada
	        return save(alumnoBuscado);
	}
	
	
	
}
