package br.com.magnasistemas.projetotodo.dtos;

import java.util.List;
import br.com.magnasistemas.projetotodo.repositories.UsuarioRepositories;
import br.com.magnasistemas.projetotodo.entities.Todo;

public class UsuarioDto {

	private String usuario;
	private String senha;
	private List<Todo> todoList;
	
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
	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
	
	
	
}
