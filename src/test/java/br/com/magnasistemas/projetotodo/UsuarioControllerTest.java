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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.magnasistemas.projetotodo.configs.PageableResponse;
import br.com.magnasistemas.projetotodo.dtos.UsuarioDto;
import br.com.magnasistemas.projetotodo.entities.UsuarioEntity;
import br.com.magnasistemas.projetotodo.repositories.UsuarioRepositories;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioRepositories usuarioRepositories;
	private UsuarioEntity usuario;

	@BeforeAll
	void start() {
		usuarioRepositories.deleteAll();
		usuario = usuarioRepositories.save(new UsuarioEntity("joaozinho", "1234"));
	}

	@Test
	@DisplayName("retorna a lista de usuários dentro do objeto da página quando bem-sucedido")
	void list_RetornaListaDeTodosUsuarios_WhenSuccessful() {

		String usuarioEsperado = usuario.getUsuario();

		PageableResponse<UsuarioDto> usuarioPage = testRestTemplate.exchange("/usuarios", HttpMethod.GET, null,
				new ParameterizedTypeReference<PageableResponse<UsuarioDto>>() {
				}).getBody();

		Assertions.assertThat(usuarioPage).isNotNull();

		Assertions.assertThat(usuarioPage.toList()).isNotEmpty().hasSize(1);

		Assertions.assertThat(usuarioPage.toList().get(0).getUsuario()).isEqualTo(usuarioEsperado);
	}

	@Test
	@DisplayName("retorna usuário quando bem sucedido")
	void findById_RetornaUmUsuario_WhenSuccessful() {
		Long idEsperado = usuario.getId();

		UsuarioDto usuarios = testRestTemplate.getForObject("/usuarios/{usuarioId}", UsuarioDto.class, idEsperado);

		Assertions.assertThat(usuarios).isNotNull();
		Assertions.assertThat(usuarios.getId()).isNotNull().isEqualTo(idEsperado);
	}

	@Test
	@DisplayName("salva um usuário quando bem-sucedido")
	void save_SalvaUmNovoUsuario_WhenSuccessful() {
		ResponseEntity<UsuarioDto> usuarios = testRestTemplate.postForEntity("/usuarios", usuario, UsuarioDto.class);
		Assertions.assertThat(usuarios).isNotNull();
		Assertions.assertThat(usuarios.getStatusCode()).isEqualTo(HttpStatus.CREATED);

	}

	@Test
	@DisplayName("atualiza as informações do usuário quando for bem-sucedido")
	void replace_AtualizaUmUsuario_WhenSuccessful() {

		usuario.setUsuario("Novo");
		ResponseEntity<Void> usuarios = testRestTemplate.exchange("/usuarios/{id}", HttpMethod.PUT,
				new HttpEntity<>(usuario), Void.class, usuario.getId());
		Assertions.assertThat(usuarios).isNotNull();
		Assertions.assertThat(usuarios.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@DisplayName("deleta o usuário quando bem-sucedido")
	void delete_DeletaUmUsuario_WhenSuccessful() {

		ResponseEntity<Void> usuarios = testRestTemplate.exchange("/usuarios/{id}", HttpMethod.DELETE, null, Void.class,
				usuario.getId());

		Assertions.assertThat(usuarios).isNotNull();

		Assertions.assertThat(usuarios.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
