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
import com.utp.microservicios.app.notas.repository.NotaRepository;
import com.utp.microservicios.app.notas.services.NotaService;

@DataJpaTest
class NotaRepositoryTest {

	@Autowired
	NotaRepository notaRepository;

	@Test
	void testFindAll() {
		List<Nota> notas = notaRepository.findAll();
		assertFalse(notas.isEmpty());
		assertEquals(5, notas.size());
	}

	@Test
	void testFindById() {
		Optional<Nota> nota = notaRepository.findById(1);
		assertTrue(nota.isPresent());
		assertEquals("Francisco", nota.orElseThrow().getAlumno().getNombre());
	}

	@Test
	void testFindByNotaxAlumno() {
		List<Nota> listado = notaRepository.findByNotaxAlumno(1);
		assertFalse(listado.isEmpty());
		assertEquals(4, listado.size());
	}

	@Test
	void testfindByNotaxAlumnoCurso() {
		List<Nota> listado = notaRepository.findByNotaxAlumnoCurso(1, 1);
		assertFalse(listado.isEmpty());
		assertEquals(1, listado.size());
	}
	
	@Test
	void testSave() {
		Nota notaNueva = new Nota(new Alumno(1), new Curso(2), 12,11,20,12,11);
		Nota nota = notaRepository.save(notaNueva);
		assertEquals(1, nota.getAlumno().getId());
		assertEquals(2, nota.getCurso().getId());
		assertEquals(12, nota.getPractica1());
		assertEquals(11, nota.getPractica2());
		assertEquals(20, nota.getPractica3());
		assertEquals(12, nota.getPractica4());
		assertEquals(11, nota.getExamenFinal());
		assertEquals(6, nota.getId());
	}

	@Test
	void testDelete() {
		Nota nota = notaRepository.findById(1).orElseThrow();
		assertEquals(1, nota.getId());

		notaRepository.delete(nota);
		assertThrows(NoSuchElementException.class, () -> {
			notaRepository.findById(1).orElseThrow();
		});
		assertEquals(4, notaRepository.findAll().size());
	}
}
