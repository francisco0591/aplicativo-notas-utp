package com.utp.microservicios.app.notas.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.utp.microservicios.app.notas.dao.NotaDao;
import com.utp.microservicios.app.notas.entity.Nota;
import com.utp.microservicios.app.notas.repository.NotaRepository;

@Repository
public class NotaDaoImpl implements NotaDao {

	@Autowired
	private NotaRepository notaRepository;

	@Override
	public List<Nota> findAll() {
		return this.notaRepository.findAll();
	}

	@Override
	public Optional<Nota> findById(int id) {
		return this.notaRepository.findById(id);
	}

	@Override
	public Nota save(Nota nota) {
		return this.notaRepository.save(nota);
	}

	@Override
	public void deleteById(int id) {
		this.notaRepository.deleteById(id);
	}

	@Override
	public List<Nota> findByNotaxAlumnoCurso(int usuario, int curso) {
		return this.notaRepository.findByNotaxAlumnoCurso(usuario, curso);
	}

	@Override
	public List<Nota> findByNotaxAlumno(int alumno) {
		return this.notaRepository.findByNotaxAlumno(alumno);
	}

}
