package com.utp.microservicios.app.notas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.utp.microservicios.app.notas.dto.AlumnoDto;
import com.utp.microservicios.app.notas.entity.Nota;
import com.utp.microservicios.app.notas.services.NotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.*;

/**
 * Clase Controller Nota para los servicios nota
 * 
 * @author Francisco Julca
 */
@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping(value = "notas")
public class NotaController {

	@Autowired
	private NotaService notaService;

	@Operation(summary = "Listar Nota", description = "Servicio que lista toda las notas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Nota> notas = notaService.findAll();
		if (notas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron notas en el sistema.");
		}
		return ResponseEntity.ok().body(notas);
	}

	@Operation(summary = "Buscar nota por id", description = "Servicio que busca las notas por su id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarxId(@PathVariable int id) {
		Optional<Nota> nota = notaService.findById(id);
		if (nota.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la nota con el ID " + id);
        }
        return ResponseEntity.ok(nota.get());
	}

	@Operation(summary = "Registra nota", description = "Servicio que registra las notas de los alumnos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody Nota nota, BindingResult result) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Nota notaDb = notaService.save(nota);
		return ResponseEntity.status(HttpStatus.CREATED).body(notaDb);

	}

	@Operation(summary = "Ver notas por alumno y curso", description = "Servicio que lista las notas por alumno y curso")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PostMapping("/verNotasxAlumnoCurso")
	public ResponseEntity<?> verNotasxAlumnoCurso(@RequestBody AlumnoDto alumno) {
		try {
			List<Nota> usuario = notaService.findByNotaxAlumnoCurso(alumno.getIdAlumno(), alumno.getIdCurso());
			if (usuario.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron notas para el alumno y curso ingresado!");
			}
			return ResponseEntity.ok().body(usuario);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Ver notas por alumno", description = "Servicio que lista las notas por alumno")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PostMapping("/verNotasxAlumno")
	public ResponseEntity<?> verNotasxAlumno(@RequestBody AlumnoDto alumno) {
		try {
			List<Nota> usuario = notaService.findByNotaxAlumno(alumno.getIdAlumno());
			if (usuario.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron notas para el alumno ingresado!");
			}
			return ResponseEntity.ok().body(usuario);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Actualizar nota", description = "Servicio que actualiza las notas de los alumnos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Nota nota, @PathVariable int id, BindingResult result) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Nota> notaOp = notaService.findById(id);
		if (notaOp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Nota notaDb = notaOp.get();
		notaDb.setPractica1(nota.getPractica1());
		return ResponseEntity.status(HttpStatus.CREATED).body(notaService.save(notaDb));
	}

	@Operation(summary = "Eliminar nota", description = "Servicio que elimina la nota por su id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) {
		notaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Calcular promedio", description = "Servicio que calcula el promedio de las notas de los alumnos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PostMapping("/promedio")
	public ResponseEntity<Integer> obtenerPromedio(@RequestBody AlumnoDto alumno) {
		try {
			int promedio = notaService.obtenerPromedioNotasAlumno(alumno.getIdAlumno(), alumno.getIdCurso());
			return new ResponseEntity<>(promedio, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(
				err -> errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errores);
	}
}
