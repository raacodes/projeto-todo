package br.com.magnasistemas.projetotodo.controller;

import org.springdoc.api.annotations.ParameterObject;
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
import br.com.magnasistemas.projetotodo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;

	
	@Operation(summary = "Listar todos as tarefas cadastrados com paginação")
	@ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso" , content = @Content(schema = @Schema(implementation = TodoDto.class))),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao listar as tarefas", content = @Content(schema = @Schema()))
    })
	@GetMapping
	public ResponseEntity<Page<TodoDto>> listarTodos(@ParameterObject @PageableDefault(size = 5) Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.findAll(page));
	}

	@ApiResponses( value ={
            @ApiResponse(responseCode = "200", description =  "Tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description =  "Não foi encontrada nenhuma tarefa com essa id", content = @Content(schema = @Schema(hidden = true)))
    })
	@Operation(summary = "Lista uma tarefa atráves da ID")
	@GetMapping("/{todoId}")
	public ResponseEntity<TodoDto> listarUmaNota(@PathVariable Long todoId) {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.findById(todoId));
	}

	
	@ApiResponses( value ={
            @ApiResponse(responseCode = "200", description =  "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description =  "Não foi possivel atualizar a tarefa - tarefa não foi encontrada", content = @Content(schema = @Schema(hidden = true)))

    })
	@Operation(summary = "Atualiza uma tarefa existente pela ID")
	@PutMapping("/{todoId}")
	public ResponseEntity<TodoDto> atualizaNota(@PathVariable Long todoId, @RequestBody TodoDto todoDto) {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.atualizarNota(todoId, todoDto));
	}

	@ApiResponses( value ={
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao criar a tarefa, verifique as informações", content = @Content(schema = @Schema(hidden = true)))

    })
	@Operation(summary = "Cadastra uma nova tarefa")
	@PostMapping("/{usuarioId}")
	public ResponseEntity<TodoDto> notaCadastro(@PathVariable Long usuarioId, @RequestBody TodoDto todoDto) {
		todoService.salvaNotaUsuario(usuarioId, todoDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiResponses( value ={
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Não foi possivel deletar a tarefa - tarefa não foi encontrada", content = @Content(schema = @Schema(hidden = true)))

    })
	@Operation(summary = "Deleta uma tarefa existente pela ID")
	@DeleteMapping("/{todoId}")
	public ResponseEntity<TodoDto> deleteUsuario(@PathVariable Long todoId) {
		todoService.deletarTodo(todoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}