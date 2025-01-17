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
import com.utp.microservicios.app.notas.repository.NotaRepository;
import com.utp.microservicios.app.notas.services.NotaService;

@SpringBootTest
public class NotaServiceTest {

	@MockBean
	NotaRepository notaRepository;

	@Autowired
	NotaService notaService;
	
	
	@Test
	void testFindAll() {
		List<Nota> datos= Arrays.asList(new Nota(new Alumno(1), new Curso(2), 12,11,20,12,11),
				new Nota(new Alumno(1), new Curso(1), 12,17,20,12,20),
				new Nota(new Alumno(1), new Curso(3), 12,12,20,12,18),
				new Nota(new Alumno(1), new Curso(4), 12,18,20,12,18));
		
		when(notaService.findAll()).thenReturn(datos);
		
		List<Nota> notas = notaService.findAll();
		
		assertFalse(notas.isEmpty());
		assertEquals(4, notas.size());
		
		verify(notaRepository).findAll();
	}
	
	@Test
	void testFindById() {
		Nota nota =new Nota(new Alumno(1), new Curso(2), 12,11,20,12,11);
		when(notaRepository.findById(1)).thenReturn(Optional.of(nota));
		
        // Ejecutar el método que estamos probando
        Optional<Nota> resultadoEsperado = notaService.findById(1);

        // Verificar el resultado
        assertTrue(resultadoEsperado.isPresent());  // Verificamos que el resultado no es vacío
        assertEquals(nota, resultadoEsperado.get()); 
	}
	
	@Test
	void testDelete() {
		Nota nota = new Nota(1,new Alumno(1));
		when(notaRepository.findById(1)).thenReturn(Optional.of(nota));
		
        Optional<Nota> elementoEncontrado = notaService.findById(1);
		assertEquals(1, elementoEncontrado.get().getId());
		
		notaService.deleteById(1);
        when(notaRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Nota> elementoEliminado = notaService.findById(1);
		assertFalse(elementoEliminado.isPresent());

	}
	
	@Test
	void testFindByNotaxAlumno() {
		List<Nota> datos= Arrays.asList(new Nota(new Alumno(1), new Curso(2), 12,11,20,12,11),
				new Nota(new Alumno(1), new Curso(1), 12,17,20,12,20),
				new Nota(new Alumno(1), new Curso(3), 12,12,20,12,18),
				new Nota(new Alumno(1), new Curso(4), 12,18,20,12,18));
		when(notaRepository.findByNotaxAlumno(1)).thenReturn(datos);
		
        // Ejecutar el método que estamos probando
        List<Nota> listaNotas = notaService.findByNotaxAlumno(1);

        assertFalse(listaNotas.isEmpty());
		assertEquals(4, listaNotas.size());
	}
	
	@Test
	void testSave() {
		Nota notaNueva = new Nota(new Alumno(1), new Curso(2), 12,11,20,12,11);
		when(notaRepository.save(any())).then(invocation ->{
			Nota c = invocation.getArgument(0);
			c.setId(6);
			return c;
		});
		
		Nota nota = notaService.save(notaNueva);
		assertEquals(1, nota.getAlumno().getId());
		assertEquals(2, nota.getCurso().getId());
		assertEquals(12, nota.getPractica1());
		assertEquals(11, nota.getPractica2());
		assertEquals(20, nota.getPractica3());
		assertEquals(12, nota.getPractica4());
		assertEquals(11, nota.getExamenFinal());
		assertEquals(6, nota.getId());
		verify(notaRepository).save(any());
	}
}
