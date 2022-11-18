package br.com.magnasistemas.projetotodo.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;

public class TodoDto {

	private Long id;
	@Schema(example = "Incluindo uma nota tarefa")
	private String titulo;
	@Schema(example = "Incluindo uma descrição")
	private String descricao;
	private UsuarioEntity usuarioEntity;
	@Schema(example = "AGUARDANDO")
	private Status status;
	private LocalDateTime dataCriacao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@JsonIgnore
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	
	
	

}