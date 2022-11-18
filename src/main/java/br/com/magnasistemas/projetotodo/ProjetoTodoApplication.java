package br.com.magnasistemas.projetotodo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.magnasistemas.projetotodo.service.TodoService;

@SpringBootApplication
public class ProjetoTodoApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(ProjetoTodoApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("iniciando API todo");
		SpringApplication.run(ProjetoTodoApplication.class, args);
		LOGGER.info("API iniciada com sucesso");
	}

}
