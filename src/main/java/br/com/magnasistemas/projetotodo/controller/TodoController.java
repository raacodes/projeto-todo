package br.com.magnasistemas.projetotodo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.magnasistemas.projetotodo.entities.TodoEntity;
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.service.TodoService;
import br.com.magnasistemas.projetotodo.service.UsuarioService;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<TodoEntity>> listarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.findAll());
	}

	@GetMapping("/{todoId}")
	public ResponseEntity<Object> listarUmaNota(@PathVariable Long todoId) {
		Optional<TodoEntity> notaOptional = todoService.findById(todoId);
		return ResponseEntity.status(HttpStatus.OK).body(notaOptional.get());
	}

	@PutMapping("/{todoId}")
	public ResponseEntity<Object> atualizaNota(@PathVariable Long todoId, @RequestBody TodoDto todoDto) {
		Optional<TodoEntity> notaOptional = todoService.findById(todoId);
		var todo = new TodoEntity();
		BeanUtils.copyProperties(todoDto, todo);
		todo.setId(notaOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(todoService.save(todo));
	}
	
	@PostMapping("/{usuarioId}")
	public ResponseEntity<Object> notaCadastro(@PathVariable Long usuarioId, @RequestBody TodoDto todoDto){
		UsuarioEntity usuario = usuarioService.findById(usuarioId).orElseThrow(NoSuchElementException::new);
		var todo = new TodoEntity();
		BeanUtils.copyProperties(todoDto, todo);
		todo.setNota(todoDto.getNota());
		usuario.getTodoList().add(todo);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }
	
	@DeleteMapping("/{usuarioId}/{todoId}")
	public ResponseEntity<Object> deleteNota(@PathVariable Long usuarioId,@PathVariable Long todoId){
        UsuarioEntity usuario = usuarioService.findById(usuarioId).orElseThrow(NoSuchElementException::new);
        TodoEntity todo = todoService.findById(todoId).orElseThrow(NoSuchElementException::new);
        usuario.getTodoList().remove(todo);
        todoService.delete(todo);
        return ResponseEntity.status(HttpStatus.OK).body("Nota deletado com sucesso!");
    }
	
}
