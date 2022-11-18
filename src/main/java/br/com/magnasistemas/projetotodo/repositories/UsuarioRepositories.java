package br.com.magnasistemas.projetotodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;

@Repository
public interface UsuarioRepositories extends JpaRepository<UsuarioEntity, Long>{

}
