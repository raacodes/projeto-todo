package br.com.magnasistemas.projetotodo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.repositories.UsuarioRepositories;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepositories usuarioRepositories;

	@Transactional
	public UsuarioEntity save(UsuarioEntity usuario) {
		return usuarioRepositories.save(usuario);
	}

	public List<UsuarioEntity> findAll() {
		return usuarioRepositories.findAll();
	}

	public Optional<UsuarioEntity> findById(Long id) {
		return usuarioRepositories.findById(id);
	}

	@Transactional
	public void delete(UsuarioEntity usuario) {
		usuarioRepositories.delete(usuario);
		
	}
}
