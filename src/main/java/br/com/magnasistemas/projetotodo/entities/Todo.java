package br.com.magnasistemas.projetotodo.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.magnasistemas.projetotodo.enums.Status;

@Entity
@Table(name = "tb_todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nota;
	@Enumerated(value = EnumType.STRING)
	private Status status = Status.NAO_INICIADO;
	
	
	public Todo() {
		
	}
	
	public Todo(Long id, String nota, Status modelo) {
		this.id = id;
		this.nota = nota;
		this.status = modelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Status getModelo() {
		return status;
	}

	public void setModelo(Status modelo) {
		this.status = modelo;
	}
	
	
	
	
}
