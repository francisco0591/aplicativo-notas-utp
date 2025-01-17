package com.utp.microservicios.app.notas.dao.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.utp.microservicios.app.notas.dao.CursoDao;
import com.utp.microservicios.app.notas.entity.Curso;
import com.utp.microservicios.app.notas.repository.CursoRepository;

@Repository
public class CursoDaoImpl implements CursoDao {

	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public List<Curso> findAll() {
		return this.cursoRepository.findAll();
	}

	@Override
	public Optional<Curso> findById(int id) {
		return this.cursoRepository.findById(id);
	}

	@Override
	public Curso save(Curso nota) {
		return this.cursoRepository.save(nota);
	}

	@Override
	public void deleteById(int id) {
		this.cursoRepository.deleteById(id);
	}

}
