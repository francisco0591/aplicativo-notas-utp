package com.utp.microservicios.app.notas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.utp.microservicios.app.notas.entity.Alumno;
import com.utp.microservicios.app.notas.entity.Curso;
import com.utp.microservicios.app.notas.entity.Nota;
import com.utp.microservicios.app.notas.entity.Usuario;
import com.utp.microservicios.app.notas.repository.AlumnoRepository;
import com.utp.microservicios.app.notas.repository.NotaRepository;
import com.utp.microservicios.app.notas.services.AlumnoService;
import com.utp.microservicios.app.notas.services.NotaService;

@SpringBootTest
public class AlumnoServiceTest {

	@MockBean
	AlumnoRepository alumnoRepository;

	@Autowired
	AlumnoService alumnoService;
	
	
	@Test
	void testFindAll() {
		List<Alumno> datos= Arrays.asList(new Alumno("Juan", "Sanchez Sosa", "46745912", "juans@gmail.com", new Usuario(1)),
				new Alumno("Juana", "Sosa Wong", "46745788", "juana22@gmail.com", new Usuario(2)));
		
		when(alumnoService.findAll()).thenReturn(datos);
		
		List<Alumno> notas = alumnoService.findAll();
		
		assertFalse(notas.isEmpty());
		assertEquals(2, notas.size());
		
		verify(alumnoRepository).findAll();
	}
	
	@Test
	void testFindById() {
		Alumno nota =new Alumno("Juan", "Sanchez Sosa", "46745912", "juans@gmail.com", new Usuario(1));
		when(alumnoRepository.findById(1)).thenReturn(Optional.of(nota));
		
        // Ejecutar el método que estamos probando
        Optional<Alumno> resultadoEsperado = alumnoService.findById(1);

        // Verificar el resultado
        assertTrue(resultadoEsperado.isPresent());  // Verificamos que el resultado no es vacío
        assertEquals(nota, resultadoEsperado.get()); 
	}
	
	@Test
	void testDelete() {
		Alumno alumno = new Alumno(1);
		when(alumnoRepository.findById(1)).thenReturn(Optional.of(alumno));
		
        Optional<Alumno> elementoEncontrado = alumnoService.findById(1);
		assertEquals(1, elementoEncontrado.get().getId());
		
		alumnoService.deleteById(1);
        when(alumnoRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Alumno> elementoEliminado = alumnoService.findById(1);
		assertFalse(elementoEliminado.isPresent());

	}
	
	
	@Test
	void testSave() {
		Alumno alumnoNuevo = new Alumno("Juan", "Sanchez Sosa", "46745912", "juans@gmail.com", new Usuario(1));
		when(alumnoRepository.save(any())).then(invocation ->{
			Alumno c = invocation.getArgument(0);
			c.setId(6);
			return c;
		});
		
		Alumno alumno = alumnoService.save(alumnoNuevo);
		assertEquals("Juan", alumno.getNombre());
		assertEquals("Sanchez Sosa", alumno.getApellido());
		assertEquals("46745912", alumno.getDni());
		assertEquals("juans@gmail.com", alumno.getEmail());
		assertEquals(1, alumno.getUsuario().getId());
		assertEquals(6, alumno.getId());
		verify(alumnoRepository).save(any());
	}
}
