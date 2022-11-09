package br.com.magnasistemas.projetotodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.magnasistemas.projetotodo.entities.Todo;
import br.com.magnasistemas.projetotodo.entities.Usuario;
import br.com.magnasistemas.projetotodo.service.TodoService;
import br.com.magnasistemas.projetotodo.service.UsuarioService;

@SpringBootApplication
public class ProjetoTodoApplication implements CommandLineRunner {

	@Autowired
	private TodoService todoService;
	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoTodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario();
		usuario.setUsuario("Renato");
		usuario.setSenha("123456");

		Todo todo = new Todo();
		todo.setNota("Testando todo list");

		usuario.getTodoList().add(todo);

		usuarioService.save(usuario);
		todoService.save(todo);
	}

}
