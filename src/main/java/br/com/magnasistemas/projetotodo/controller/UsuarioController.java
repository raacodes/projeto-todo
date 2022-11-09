package br.com.magnasistemas.projetotodo.controller;

import java.util.List;
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

import br.com.magnasistemas.projetotodo.dtos.UsuarioDto;
import br.com.magnasistemas.projetotodo.entities.Usuario;
import br.com.magnasistemas.projetotodo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<Object> listarUmUsuario(@PathVariable Long usuarioId) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(usuarioId);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}

	@PostMapping
	public ResponseEntity<Object> usuarioCadastro(@RequestBody UsuarioDto usuarioDto) {
		var usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable Long usuarioId){
		Optional<Usuario> usuarioOptional = usuarioService.findById(usuarioId);
		usuarioService.delete(usuarioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
	}
	
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Object> atualizaUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioDto usuarioDto){
		Optional<Usuario> usuarioOptional = usuarioService.findById(usuarioId);
		var usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		usuario.setId(usuarioOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
	}
	
}
