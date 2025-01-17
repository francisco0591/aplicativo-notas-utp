package com.utp.microservicios.app.notas.entity;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase Entity Usuario
 * 
 * @author Francisco Julca
 */
@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@AllArgsConstructor
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String usuario;
	@JsonIgnore
	private String clave;
	private String roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Stream.of(roles.split(", ")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

	}

	public Usuario() {
	}

	public Usuario(int id) {
		this.id = id;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return clave;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}