package br.com.magnasistemas.projetotodo.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.projetotodo.dtos.UsuarioDto;
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.repositories.UsuarioRepositories;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepositories usuarioRepositories;

	public Page<UsuarioDto> listarUsuarios(Pageable page) {
		return usuarioRepositories.findAll(page).map(this::converterEntityParaDTO);
	}

	public UsuarioDto findById(Long id) {
		UsuarioEntity usuario = usuarioRepositories.findById(id).orElseThrow(NoSuchElementException::new);
		return converterEntityParaDTO(usuario);
	}

//	@Transactional
//	public void salvarNovoUsuario(UsuarioDto usuariodto) {
//		usuarioRepositories.save(converterDTOParaEntity(usuariodto));
//	}
	
	@Transactional
	public void salvarNovoUsuario(UsuarioDto usuariodto) {
		usuarioRepositories.save(converterDTOParaEntity(usuariodto));
	}

	@Transactional
	public void deletarUsuario(Long id) {
		usuarioRepositories.deleteById(id);
	}

	@Transactional
	public UsuarioDto atualizarUsuario(Long id, UsuarioDto usuarioDto) {
		UsuarioEntity entidade = usuarioRepositories.findById(id).map(item -> {
			item.setUsuario(usuarioDto.getUsuario());
			item.setSenha(usuarioDto.getSenha());
			return item;
		}).orElseThrow(NoSuchElementException::new);

		return converterEntityParaDTO(entidade);
	}

	public UsuarioEntity converterDTOParaEntity(UsuarioDto usuarioDto) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		BeanUtils.copyProperties(usuarioDto, usuarioEntity);
		return usuarioEntity;
	}

	public UsuarioDto converterEntityParaDTO(UsuarioEntity usuarioEntity) {
		UsuarioDto usuarioDto = new UsuarioDto();
		BeanUtils.copyProperties(usuarioEntity, usuarioDto);
		return usuarioDto;
	}
	

}