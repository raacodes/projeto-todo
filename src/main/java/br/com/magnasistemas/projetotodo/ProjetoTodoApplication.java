package br.com.magnasistemas.projetotodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.magnasistemas.projetotodo.entities.TodoEntity;
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.service.TodoService;
import br.com.magnasistemas.projetotodo.service.UsuarioService;

@SpringBootApplication
public class ProjetoTodoApplication {

	@Autowired
	private TodoService todoService;
	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoTodoApplication.class, args);
	}

}
