package com.utp.microservicios.app.notas.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.utp.microservicios.app.notas.dto.AuthRequest;
import com.utp.microservicios.app.notas.dto.AuthResponse;
import com.utp.microservicios.app.notas.entity.Usuario;

public interface UserService {

	ResponseEntity<AuthResponse> login(AuthRequest authRequest);
	List<Usuario> listarUsuario();
}
