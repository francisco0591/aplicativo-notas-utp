package com.utp.microservicios.app.notas.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.utp.microservicios.app.notas.entity.Alumno;
import com.utp.microservicios.app.notas.entity.Curso;
import com.utp.microservicios.app.notas.entity.Nota;
import com.utp.microservicios.app.notas.entity.Usuario;
import com.utp.microservicios.app.notas.repository.NotaRepository;
import com.utp.microservicios.app.notas.services.NotaService;

@DataJpaTest
class CursoRepositoryTest {

	@Autowired
	CursoRepository cursoRepository;

	@Test
	void testFindAll() {
		List<Curso> cursos = cursoRepository.findAll();
		assertFalse(cursos.isEmpty());
		assertEquals(5, cursos.size());
	}

	@Test
	void testFindById() {
		Optional<Curso> curso = cursoRepository.findById(1);
		assertTrue(curso.isPresent());
		assertEquals("Algoritmos", curso.orElseThrow().getNombre());
	}


	@Test
	void testSave() {
		Curso cursoNuevo = new Curso("Matematica");
		Curso curso = cursoRepository.save(cursoNuevo);
		assertEquals("Matematica", curso.getNombre());
		assertEquals(6, curso.getId());
	}

	
}
