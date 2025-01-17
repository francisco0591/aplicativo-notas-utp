package com.utp.microservicios.app.notas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utp.microservicios.app.notas.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.usuario=?1")
	Usuario findByUsuario(String usuario);

	List<Usuario> findAll();
}
