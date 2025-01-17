package com.utp.microservicios.app.notas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.utp.microservicios.app.notas.dto.AuthRequest;
import com.utp.microservicios.app.notas.services.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

/**
Clase Controller Usuario para los servicios usuario
@author  Francisco Julca
*/
@RestController
public class UsuarioController extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	
	@Operation(summary = "Login", description = "Servicio para acceder a los microservicios notas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio ejecutado correctamente"),
			@ApiResponse(code = 401, message = "Servicio no autorizado"),
			@ApiResponse(code = 403, message = "Servicio restringido"),
			@ApiResponse(code = 404, message = "Servicio no encontrado"),
			@ApiResponse(code = 500, message = "Servicio error interno") })
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		try {
			return userService.login(authRequest);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(userService.listarUsuario());
	}
}
