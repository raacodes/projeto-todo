package br.com.magnasistemas.projetotodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magnasistemas.projetotodo.entities.TodoEntity;

@Repository
public interface TodoRepositories extends JpaRepository<TodoEntity, Long>{

}
