package com.utp.microservicios.app.notas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.utp.microservicios.app.notas.entity.Alumno;
import com.utp.microservicios.app.notas.services.AlumnoService;
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
@RequestMapping(value = "alumnos")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;

	@Operation(summary = "Listar Alumno", description = "Servicio que lista toda los alumnos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Alumno> alumnos = alumnoService.findAll();
		if (alumnos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alumnos en el sistema.");
		}
		return ResponseEntity.ok().body(alumnos);
	}

	@Operation(summary = "Buscar alumno por id", description = "Servicio que busca los alumnos por su id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarxId(@PathVariable int id) {
		Optional<Alumno> alumno = alumnoService.findById(id);
		if (alumno.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el alumno con el ID " + id);
		}
		return ResponseEntity.ok(alumno.get());
	}

	@Operation(summary = "Registra alumno", description = "Servicio que registra  los alumnos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody Alumno alumno, BindingResult result) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Alumno alumnoDb = alumnoService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);

	}

	@Operation(summary = "Ver alumno por usuario", description = "Servicio que lista los alumnos por usuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@GetMapping("/verAlumnoxUsuario/{idUsuario}")
	public ResponseEntity<?> verAlumnoxUsuario(@PathVariable int idUsuario) {
		try {
			List<Alumno> alumno = alumnoService.obtenerAlumnoPorUsuario(idUsuario);
			if (alumno.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().body(alumno);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Actualizar alumno", description = "Servicio que actualiza los alumnos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody Alumno alumno, @PathVariable int id) {
		Alumno alumnoDB = this.alumnoService.actualizarAlumno(id, alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDB);
	}

	@Operation(summary = "Eliminar alumno", description = "Servicio que elimina el alumno por su id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) {
		alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}


	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(
				err -> errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errores);
	}
}
