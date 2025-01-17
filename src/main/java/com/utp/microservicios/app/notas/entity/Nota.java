package com.utp.microservicios.app.notas.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Clase Entity Nota
 * 
 * @author Francisco Julca
 */
@Entity
@Table(name = "NOTA")
public class Nota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "alumno_id")
	@NotNull
	private Alumno alumno;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	@NotNull
	private Curso curso;

	@Min(0)@Max(20)
	private int practica1;
	@Min(0)@Max(20)
	private int practica2;
	@Min(0)@Max(20)
	private int practica3;
	@Min(0)@Max(20)
	private int practica4;
	@Min(0)@Max(20)
	@Column(name = "examen_final")
	private int examenFinal;

	public Nota() {

	}

	public Nota(Alumno alumno, Curso curso, int practica1, int practica2, int practica3, int practica4,
			int examenFinal) {
		this.alumno = alumno;
		this.curso = curso;
		this.practica1 = practica1;
		this.practica2 = practica2;
		this.practica3 = practica3;
		this.practica4 = practica4;
		this.examenFinal = examenFinal;
	}

	public Nota(int id, Alumno alumno) {
		super();
		this.id = id;
		this.alumno = alumno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getPractica1() {
		return practica1;
	}

	public void setPractica1(int practica1) {
		this.practica1 = practica1;
	}

	public int getPractica2() {
		return practica2;
	}

	public void setPractica2(int practica2) {
		this.practica2 = practica2;
	}

	public int getPractica3() {
		return practica3;
	}

	public void setPractica3(int practica3) {
		this.practica3 = practica3;
	}

	public int getPractica4() {
		return practica4;
	}

	public void setPractica4(int practica4) {
		this.practica4 = practica4;
	}

	public int getExamenFinal() {
		return examenFinal;
	}

	public void setExamenFinal(int examenFinal) {
		this.examenFinal = examenFinal;
	}

}
