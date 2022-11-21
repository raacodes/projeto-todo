package br.com.magnasistemas.projetotodo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.magnasistemas.projetotodo.configs.PageableResponse;
import br.com.magnasistemas.projetotodo.dtos.TodoDto;
import br.com.magnasistemas.projetotodo.entities.TodoEntity;
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.repositories.TodoRepositories;
import br.com.magnasistemas.projetotodo.repositories.UsuarioRepositories;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@LocalServerPort
	private int port;

	@Autowired
	private TodoRepositories todoRepositories;
	@Autowired
	private UsuarioRepositories usuarioRepositories;

	private TodoEntity todo;
	private UsuarioEntity usuario;

	@BeforeAll
	void start() {
		todoRepositories.deleteAll();
		todo = todoRepositories.save(new TodoEntity("Nova Tarefa", "Testando"));
		usuario = usuarioRepositories.save(new UsuarioEntity("clebinho", "1234"));
	}

	@Test
	@DisplayName("retorna a lista de tarefas dentro do objeto da página quando bem-sucedido")
	void lista_RetornaListaDeTodasTarefas_WhenSuccessful() {

		String tituloEsperado = todo.getTitulo();

		PageableResponse<TodoDto> tarefas = testRestTemplate
				.exchange("/todos", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<TodoDto>>() {
				}).getBody();

		Assertions.assertThat(tarefas).isNotNull();

		Assertions.assertThat(tarefas.toList()).isNotEmpty().hasSize(1);

		Assertions.assertThat(tarefas.toList().get(0).getTitulo()).isEqualTo(tituloEsperado);
	}

	@Test
	@DisplayName("findById retorna tarefa quando bem sucedido")
	void findById_RetornaUmaTarefa_WhenSuccessful() {
		Long idEsperado = todo.getId();

		TodoDto tarefas = testRestTemplate.getForObject("/todos/{Id}", TodoDto.class, idEsperado);

		Assertions.assertThat(tarefas).isNotNull();
		Assertions.assertThat(tarefas.getId()).isNotNull().isEqualTo(idEsperado);
	}

	@Test
	@DisplayName("salva uma nota quando bem-sucedido")
	void save_RetornaUmaNovaTarefa_WhenSuccessful() {
		Long idEsperado = usuario.getId();
		ResponseEntity<TodoDto> tarefas = testRestTemplate.postForEntity("/todos/{Id}", todo, TodoDto.class,
				idEsperado);
		Assertions.assertThat(tarefas).isNotNull();
		Assertions.assertThat(tarefas.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	@DisplayName("atualiza as informações da tarefa quando for bem-sucedido")
	void replace_AtualizaUmaTarefa_WhenSuccessful() {

		todo.setTitulo("new");

		ResponseEntity<Void> tarefas = testRestTemplate.exchange("/todos/{id}", HttpMethod.PUT,
				new HttpEntity<>(todo), Void.class, todo.getId());

		Assertions.assertThat(tarefas).isNotNull();

		Assertions.assertThat(tarefas.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@DisplayName("deleta a tarefa quando bem-sucedido")
	void delete_DeletaUmaTarefa_WhenSuccessful() {

		ResponseEntity<Void> tarefas = testRestTemplate.exchange("/todos/{id}", HttpMethod.DELETE, null,
				Void.class, todo.getId());

		Assertions.assertThat(tarefas).isNotNull();

		Assertions.assertThat(tarefas.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
