package br.com.magnasistemas.projetotodo.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<Page<UsuarioDto>> listarUsuarios(@PageableDefault(size = 5) Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios(page));
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> listarUmUsuario(@PathVariable Long usuarioId) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(usuarioId));
	}

	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> deleteUsuario(@PathVariable Long usuarioId) {
		usuarioService.deletarUsuario(usuarioId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> usuarioCadastro(@RequestBody UsuarioDto usuarioDto) {
		usuarioService.salvarNovoUsuario(usuarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> atualizaUsuario(@PathVariable Long usuarioId,
			@RequestBody UsuarioDto usuarioDto) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuario(usuarioId, usuarioDto));
	}

}