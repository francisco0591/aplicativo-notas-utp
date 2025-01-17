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
class AlumnoRepositoryTest {

	@Autowired
	AlumnoRepository alumnoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	NotaRepository notaRepository;

	
	@Test
	void testFindAll() {
		List<Alumno> alumnos = alumnoRepository.findAll();
		assertFalse(alumnos.isEmpty());
		assertEquals(2, alumnos.size());
	}

	@Test
	void testFindById() {
		Optional<Alumno> alumno = alumnoRepository.findById(1);
		assertTrue(alumno.isPresent());
		assertEquals("Francisco", alumno.orElseThrow().getNombre());
	}

	

	@Test
	void testSave() {
		Alumno alumnoNuevo = new Alumno("Juan", "Sanchez Sosa", "46745912", "juans@gmail.com", new Usuario(1));
		Alumno alumno = alumnoRepository.save(alumnoNuevo);
		assertEquals("Juan", alumno.getNombre());
		assertEquals("Sanchez Sosa", alumno.getApellido());
		assertEquals("46745912", alumno.getDni());
		assertEquals("juans@gmail.com", alumno.getEmail());
		assertEquals(1, alumno.getUsuario().getId());
	}

	
}
