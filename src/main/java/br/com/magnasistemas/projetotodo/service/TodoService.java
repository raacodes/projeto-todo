package br.com.magnasistemas.projetotodo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.projetotodo.dtos.TodoDto;
import br.com.magnasistemas.projetotodo.entities.TodoEntity;
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.repositories.TodoRepositories;
import br.com.magnasistemas.projetotodo.repositories.UsuarioRepositories;

@Service
public class TodoService {

	private static Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
	
	@Autowired
	TodoRepositories todoRepositories;
	@Autowired
	UsuarioRepositories usuarioRepositories;

	public Page<TodoDto> findAll(Pageable page) {
		LOGGER.info("Listando tarefas cadastradas");
		return todoRepositories.findAll(page).map(this::converterEntityParaDTO);
	}

	public TodoDto findById(Long id) {
		TodoEntity todo = todoRepositories.findById(id).orElseThrow(NoSuchElementException::new);
		LOGGER.info("Buscando tarefa com o id: [{}]", id);
		return converterEntityParaDTO(todo);
	}

	public void salvaNotaUsuario(Long usuarioId, TodoDto todoDto) {
		Optional<UsuarioEntity> usuario = usuarioRepositories.findById(usuarioId);
		TodoDto todo = new TodoDto();
		todo.setTitulo(todoDto.getTitulo());
		todo.setDescricao(todoDto.getDescricao());
		todo.setStatus(todoDto.getStatus());
		todo.setUsuarioEntity(usuario.get());
		todo.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
		LOGGER.info("Criando uma nova tarefa");
		todoRepositories.save(converterDTOParaEntity(todo));
	}

	@Transactional
	public void deletarTodo(Long id) {
		LOGGER.info("Deletando tarefa com id [{}]", id);
		todoRepositories.deleteById(id);
	}

	public TodoEntity converterDTOParaEntity(TodoDto todoDto) {
		TodoEntity todoEntity = new TodoEntity();
		BeanUtils.copyProperties(todoDto, todoEntity);
		return todoEntity;
	}

	public TodoDto converterEntityParaDTO(TodoEntity todoEntity) {
		TodoDto todoDto = new TodoDto();
		BeanUtils.copyProperties(todoEntity, todoDto);
		return todoDto;

	}

	@Transactional
	public TodoDto atualizarNota(Long id, TodoDto todoDto) {
		TodoEntity entidade = todoRepositories.findById(id).map(item -> {
			item.setTitulo(todoDto.getTitulo());
			item.setDescricao(todoDto.getDescricao());
			item.setStatus(todoDto.getStatus());
			return item;
		}).orElseThrow(NoSuchElementException::new);
		LOGGER.info("Atualizando tarefa com id: [{}] ", id);
		return converterEntityParaDTO(entidade);
	}

}