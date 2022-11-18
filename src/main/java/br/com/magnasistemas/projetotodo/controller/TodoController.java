package br.com.magnasistemas.projetotodo.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magnasistemas.projetotodo.dtos.TodoDto;
import br.com.magnasistemas.projetotodo.dtos.UsuarioDto;
import br.com.magnasistemas.projetotodo.entities.TodoEntity;
import br.com.magnasistemas.projetotodo.service.TodoService;
import br.com.magnasistemas.projetotodo.service.UsuarioService;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping
	public ResponseEntity<Page<TodoDto>> listarTodos(@PageableDefault(size = 5) Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.findAll(page));
	}

	@GetMapping("/{todoId}")
	public ResponseEntity<TodoDto> listarUmaNota(@PathVariable Long todoId) {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.findById(todoId));
	}

	@PutMapping("/{todoId}")
	public ResponseEntity<TodoDto> atualizaNota(@PathVariable Long todoId, @RequestBody TodoDto todoDto) {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.atualizarNota(todoId, todoDto));
	}

	@PostMapping("/{usuarioId}")
	public ResponseEntity<TodoDto> notaCadastro(@PathVariable Long usuarioId, @RequestBody TodoDto todoDto) {
		todoService.salvaNotaUsuario(usuarioId, todoDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{todoId}")
	public ResponseEntity<TodoDto> deleteUsuario(@PathVariable Long todoId) {
		todoService.deletarTodo(todoId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}