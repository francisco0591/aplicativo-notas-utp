package com.utp.microservicios.app.notas.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.utp.microservicios.app.notas.dao.AlumnoDao;
import com.utp.microservicios.app.notas.entity.Alumno;
import com.utp.microservicios.app.notas.repository.AlumnoRepository;

@Repository
public class AlumnoDaoImpl implements AlumnoDao {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Override
	public List<Alumno> findAll() {
		return this.alumnoRepository.findAll();
	}

	@Override
	public Optional<Alumno> findById(int id) {
		return this.alumnoRepository.findById(id);
	}

	@Override
	public Alumno save(Alumno nota) {
		return this.alumnoRepository.save(nota);
	}

	@Override
	public void deleteById(int id) {
		this.alumnoRepository.deleteById(id);
	}

	@Override
	public List<Alumno> findAlumnoUsuario(int usuario) {
		return this.alumnoRepository.findByUsuario(usuario);
	}

}
