package br.com.magnasistemas.projetotodo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.magnasistemas.projetotodo.entities.Usuario;
import br.com.magnasistemas.projetotodo.entities.Todo;
import br.com.magnasistemas.projetotodo.repositories.TodoRepositories;

@Service
public class TodoService {

	@Autowired
	TodoRepositories todoRepositories;

	@Transactional
	public Todo save(Todo todo) {
		return todoRepositories.save(todo);
	}

	public List<Todo> findAll() {
		return todoRepositories.findAll();
	}

	public Optional<Todo> findById(Long id) {
		return todoRepositories.findById(id);
	}

	@Transactional
	public void delete(Todo todo) {
		todoRepositories.delete(todo);

	}
}
