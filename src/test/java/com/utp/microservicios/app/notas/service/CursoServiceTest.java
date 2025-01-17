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
import com.utp.microservicios.app.notas.repository.CursoRepository;
import com.utp.microservicios.app.notas.repository.NotaRepository;
import com.utp.microservicios.app.notas.services.AlumnoService;
import com.utp.microservicios.app.notas.services.CursoService;
import com.utp.microservicios.app.notas.services.NotaService;

@SpringBootTest
public class CursoServiceTest {

	@MockBean
	CursoRepository cursoRepository;

	@Autowired
	CursoService cursoService;
	
	
	@Test
	void testFindAll() {
		List<Curso> datos= Arrays.asList(new Curso("Matematicas"),
				new Curso("Lenguaje"));
		
		when(cursoService.findAll()).thenReturn(datos);
		
		List<Curso> cursos = cursoService.findAll();
		
		assertFalse(cursos.isEmpty());
		assertEquals(2, cursos.size());
		
		verify(cursoRepository).findAll();
	}
	
	@Test
	void testFindById() {
		Curso curso =new Curso("Matematicas");
		when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
		
        // Ejecutar el método que estamos probando
        Optional<Curso> resultadoEsperado = cursoService.findById(1);

        // Verificar el resultado
        assertTrue(resultadoEsperado.isPresent());  // Verificamos que el resultado no es vacío
        assertEquals(curso, resultadoEsperado.get()); 
	}
	
	@Test
	void testDelete() {
		Curso curso = new Curso(1);
		when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
		
        Optional<Curso> elementoEncontrado = cursoService.findById(1);
		assertEquals(1, elementoEncontrado.get().getId());
		
		cursoService.deleteById(1);
        when(cursoRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Curso> elementoEliminado = cursoService.findById(1);
		assertFalse(elementoEliminado.isPresent());

	}
	
	
	@Test
	void testSave() {
		Curso cursoNuevo =new Curso("Matematicas");
		when(cursoRepository.save(any())).then(invocation ->{
			Curso c = invocation.getArgument(0);
			c.setId(6);
			return c;
		});
		
		Curso alumno = cursoService.save(cursoNuevo);
		assertEquals("Matematicas", alumno.getNombre());
		assertEquals(6, alumno.getId());
		verify(cursoRepository).save(any());
	}
}
