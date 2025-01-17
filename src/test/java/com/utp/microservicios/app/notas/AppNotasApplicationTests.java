package com.utp.microservicios.app.notas;


import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.utp.microservicios.app.notas.entity.Nota;
import com.utp.microservicios.app.notas.services.NotaService;


@SpringBootTest
class AppNotasApplicationTests {
//
//	@Autowired
//	NotaService notaService;
//
//	@Test
//	@Transactional()
//	void saveProducto() {
//		Nota nota = new Nota();
//		nota.setNota1(12);
//		nota.setNota2(14);
//		nota.setNota3(15);
//		nota.setNota4(12);
//		nota.setPromedio(0);
//		nota.setIdCurso(1);
//		nota.setIdUsuario(1);
//		Nota notaDb =notaService.save(nota);
//		assertNotNull(notaDb);
//	}
//	
//	@Test
//	@Transactional(readOnly = true)
//	void listarNota() {
//		List<Nota> listarNotas = notaService.findAll();
//		assertFalse(listarNotas.isEmpty());
//	}
//
//	@Test
//	@Transactional(readOnly = true)
//	void buscarNotaxId() {
//		Optional<Nota> nota = notaService.findById((long)1);
//		assertNotNull(nota.get());
//	}
//	
//	@Test
//	@Transactional()
//	void actualizarNota() {
//		Optional<Nota> notaOp = notaService.findById((long)1);
//		Nota notaDb = notaOp.get();
//		notaDb.setNota1(12);
//		notaDb.setNota2(11);
//		notaDb.setNota3(20);
//		notaDb.setNota4(17);
//		assertNotNull(notaService.save(notaDb));
//	}
//	
//	@Test
//	@Transactional()
//	void eliminarNotaxId() {
//		notaService.deleteById((long)1);
//		Optional<Nota> nota = notaService.findById((long)1);
//		assertEquals(true,nota.isEmpty());
//	}

	
}
