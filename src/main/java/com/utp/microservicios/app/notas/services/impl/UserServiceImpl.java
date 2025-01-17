package com.utp.microservicios.app.notas.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.utp.microservicios.app.notas.dto.AuthRequest;
import com.utp.microservicios.app.notas.dto.AuthResponse;
import com.utp.microservicios.app.notas.entity.Usuario;
import com.utp.microservicios.app.notas.repository.UsuarioRepository;
import com.utp.microservicios.app.notas.security.jwt.JWTUtil;
import com.utp.microservicios.app.notas.security.jwt.PBKDF2Encoder;
import com.utp.microservicios.app.notas.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PBKDF2Encoder passwordEncoder;

	/**
	 Metodo para loguearse al sistema 
	 @author  Francisco Julca
	 */
	@Override
	public ResponseEntity<AuthResponse> login(AuthRequest authRequest) {
		//obtiene el usuario por el password ingresando en el request
	    Usuario u = usuarioRepository.findByUsuario(authRequest.getPassword());
	    //valida si existe el usuario
		if (u != null) {
			// valida los passwords si son iguales convertidos en base 64
			if (passwordEncoder.encode(authRequest.getPassword()).equals(u.getPassword())) {
				//si son iguales genera el token de acceso
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u)));
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public List<Usuario> listarUsuario() {
		return usuarioRepository.findAll();
	}
}
