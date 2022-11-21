package br.com.magnasistemas.projetotodo.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.magnasistemas.projetotodo.entities.TodoEntity;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto {

	private Long id;
	@Schema(example = "user")
	private String usuario;
	@Schema(example = "password")
	private String senha;
	private List<TodoEntity> todoList;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
