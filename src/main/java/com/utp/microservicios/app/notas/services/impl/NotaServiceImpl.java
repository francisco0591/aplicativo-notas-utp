package com.utp.microservicios.app.notas.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.utp.microservicios.app.notas.dao.NotaDao;
import com.utp.microservicios.app.notas.entity.Nota;
import com.utp.microservicios.app.notas.services.NotaService;

@Service
public class NotaServiceImpl implements NotaService {

	@Autowired
	private NotaDao notaDAO;

	/**
	 * Metodo para obtener el listado de notas
	 * 
	 * @author Francisco Julca
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Nota> findAll() {
		// TODO Auto-generated method stub
		return this.notaDAO.findAll();
	}

	/**
	 * Metodo para obtener el registro de la nota por id
	 * 
	 * @author Francisco Julca
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Nota> findById(int id) {
		// TODO Auto-generated method stub
		return this.notaDAO.findById(id);
	}

	/**
	 * Metodo para registrar la nota
	 * 
	 * @author Francisco Julca
	 */
	@Override
	@Transactional()
	public Nota save(Nota nota) {
		return this.notaDAO.save(nota);
	}

	/**
	 * Metodo para obtener eliminar el usuario por id
	 * 
	 * @author Francisco Julca
	 */
	@Override
	@Transactional()
	public void deleteById(int id) {
		this.notaDAO.deleteById(id);
	}

	/**
	 * Metodo para obtener el usuario por id
	 * 
	 * @author Francisco Julca
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Nota> findByNotaxAlumnoCurso(int alumno,int curso) {
		return this.notaDAO.findByNotaxAlumnoCurso(alumno,curso);
	}

	@Override
	public int obtenerPromedioNotasAlumno(int alumnoId,int cursoId) {
		List<Nota> notas = findByNotaxAlumnoCurso(alumnoId,cursoId);
		if (notas.isEmpty()) {
			throw new RuntimeException("El alumno no tiene notas registradas");
		}
		double totalNotas = 0;
        for (Nota nota : notas) {
            totalNotas += (nota.getPractica1() + nota.getPractica2() + nota.getPractica3() + 
                           nota.getPractica4() + nota.getExamenFinal());
        }
		return (int) Math.round(totalNotas / (notas.size() * 5));
	}


	@Override
	public List<Nota> findByNotaxAlumno(int alumno) {
		return this.notaDAO.findByNotaxAlumno(alumno);
	}

}
