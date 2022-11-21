package br.com.magnasistemas.projetotodo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String usuario;
	private String senha;
	@OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL)
	private List<TodoEntity> todoList = new ArrayList<>();

	public UsuarioEntity() {
	}

	public UsuarioEntity(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<TodoEntity> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoEntity> todoList) {
		this.todoList = todoList;
	}

}
