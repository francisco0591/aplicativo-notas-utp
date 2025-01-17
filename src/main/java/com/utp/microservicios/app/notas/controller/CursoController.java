package com.utp.microservicios.app.notas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.utp.microservicios.app.notas.entity.Curso;
import com.utp.microservicios.app.notas.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.HashMap;
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
 * Clase Controller Nota para los servicios curso
 * 
 * @author Francisco Julca
 */
@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping(value = "cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@Operation(summary = "Listar Curso", description = "Servicio que lista toda los cursos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(cursoService.findAll());
	}

	@Operation(summary = "Buscar curso por id", description = "Servicio que busca los cursos por su id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarxId(@PathVariable int id) {
		Optional<Curso> curso = cursoService.findById(id);
		if (curso.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(curso.get());
	}

	@Operation(summary = "Registra curso", description = "Servicio que registra los cursos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody Curso curso, BindingResult result) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Curso cursoDb = cursoService.save(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);

	}

	
	@Operation(summary = "Actualizar curso", description = "Servicio que actualiza los cursos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody Curso curso, @PathVariable int id) {
		Curso cursoDB = this.cursoService.actualizarCurso(id, curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoDB);
	}

	@Operation(summary = "Eliminar curso", description = "Servicio que elimina el curso por su id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) {
		cursoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}


	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(
				err -> errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errores);
	}
}
