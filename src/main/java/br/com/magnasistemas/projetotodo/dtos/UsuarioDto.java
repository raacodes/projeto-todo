package br.com.magnasistemas.projetotodo.dtos;

import java.util.List;

import br.com.magnasistemas.projetotodo.entities.TodoEntity;

public class UsuarioDto {

	private String usuario;
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
	
	
	
}
