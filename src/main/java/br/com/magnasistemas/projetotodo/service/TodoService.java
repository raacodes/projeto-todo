package br.com.magnasistemas.projetotodo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.projetotodo.entities.TodoEntity;
import br.com.magnasistemas.projetotodo.repositories.TodoRepositories;

@Service
public class TodoService {

	@Autowired
	TodoRepositories todoRepositories;

	@Transactional
	public TodoEntity save(TodoEntity todo) {
		return todoRepositories.save(todo);
	}

	public List<TodoEntity> findAll() {
		return todoRepositories.findAll();
	}

	public Optional<TodoEntity> findById(Long id) {
		return todoRepositories.findById(id);
	}

	@Transactional
	public void delete(TodoEntity todo) {
		todoRepositories.delete(todo);

	}
}
