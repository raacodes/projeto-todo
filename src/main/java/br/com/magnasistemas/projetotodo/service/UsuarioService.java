package br.com.magnasistemas.projetotodo.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.projetotodo.dtos.UsuarioDto;
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.exception.BadRequestException;
import br.com.magnasistemas.projetotodo.repositories.UsuarioRepositories;

@Service
public class UsuarioService {

	private static Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	UsuarioRepositories usuarioRepositories;

	public Page<UsuarioDto> listarUsuarios(Pageable page) {
		LOGGER.info("Listando usuários cadastradas");
		return usuarioRepositories.findAll(page).map(this::converterEntityParaDTO);
	}

	public UsuarioDto findById(Long id) {
		UsuarioEntity usuario = usuarioRepositories.findById(id).orElseThrow(() -> new BadRequestException("Não foi possível encontrar o usuário com o id: " + id));
		LOGGER.info("Buscando usuario com o id: [{}]", id);
		return converterEntityParaDTO(usuario);
	}

	@Transactional
	public void salvarNovoUsuario(UsuarioDto usuariodto) {
		LOGGER.info("Salvando um novo usuário");
		usuarioRepositories.save(converterDTOParaEntity(usuariodto));
	}

	@Transactional
	public void deletarUsuario(Long id) {
		LOGGER.info("Deletando o usuário com a id: [{}]", id);
		usuarioRepositories.deleteById(id);
	}

	@Transactional
	public UsuarioDto atualizarUsuario(Long id, UsuarioDto usuarioDto) {
		UsuarioEntity entidade = usuarioRepositories.findById(id).map(item -> {
			item.setUsuario(usuarioDto.getUsuario());
			item.setSenha(usuarioDto.getSenha());
			return item;
		}).orElseThrow(() -> new BadRequestException("Não foi possível encontrar o usuário com o id: " + id));
		LOGGER.info("Atualizando usuário com id: [{}] ", id);
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