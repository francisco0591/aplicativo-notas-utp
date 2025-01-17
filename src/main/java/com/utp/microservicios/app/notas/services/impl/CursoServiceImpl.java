package com.utp.microservicios.app.notas.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utp.microservicios.app.notas.dao.CursoDao;
import com.utp.microservicios.app.notas.entity.Curso;
import com.utp.microservicios.app.notas.services.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoDao cursoDao;

	@Override
	public List<Curso> findAll() {
		return this.cursoDao.findAll();
	}

	@Override
	public Optional<Curso> findById(int id) {
		return this.cursoDao.findById(id);
	}

	@Override
	public Curso save(Curso curso) {
		return this.cursoDao.save(curso);
	}

	@Override
	public void deleteById(int id) {
		this.cursoDao.deleteById(id);
	}

	@Override
	public Curso actualizarCurso(int cursoId, Curso curso) {
		Curso cursoBuscado = findById(cursoId).orElseThrow(() -> new RuntimeException("Curso no encontrado!"));
		cursoBuscado.setNombre(curso.getNombre());
		return save(cursoBuscado);
	}

}
