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

import br.com.magnasistemas.projetotodo.dtos.UsuarioDto;
import br.com.magnasistemas.projetotodo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Usuários listadas com sucesso" , content = @Content(schema = @Schema(implementation = UsuarioDto.class))),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao listar os usuários", content = @Content(schema = @Schema(hidden = true)))
    })
	@Operation(summary = "Listar todos os usuários cadastrados com paginação")
	@GetMapping
	public ResponseEntity<Page<UsuarioDto>> listarUsuarios(@ParameterObject @PageableDefault(size = 5) Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios(page));
	}

	@ApiResponses( value ={
            @ApiResponse(responseCode = "200", description =  "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description =  "Não foi encontrada nenhum usuário com essa id", content = @Content(schema = @Schema(hidden = true)))
    })
	@Operation(summary = "Lista um usuário atráves da ID")
	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> listarUmUsuario(@PathVariable Long usuarioId) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(usuarioId));
	}

	@ApiResponses( value ={
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Não foi possivel deletar o usuário - tarefa não foi encontrada", content = @Content(schema = @Schema(hidden = true)))

    })
	@Operation(summary = "Deleta um usuário existente pela ID")
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> deleteUsuario(@PathVariable Long usuarioId) {
		usuarioService.deletarUsuario(usuarioId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ApiResponses( value ={
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao criar o usuário, verifique as informações", content = @Content(schema = @Schema(hidden = true)))

    })
	@Operation(summary = "Cadastra um novo usuário")
	@PostMapping
	public ResponseEntity<UsuarioDto> usuarioCadastro(@RequestBody UsuarioDto usuarioDto) {
		usuarioService.salvarNovoUsuario(usuarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiResponses( value ={
            @ApiResponse(responseCode = "200", description =  "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description =  "Não foi possivel atualizar o usuário - tarefa não foi encontrada", content = @Content(schema = @Schema(hidden = true)))

    })
	@Operation(summary = "Atualiza um usuário existente pela ID")
	@PutMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> atualizaUsuario(@PathVariable Long usuarioId,
			@RequestBody UsuarioDto usuarioDto) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuario(usuarioId, usuarioDto));
	}

}