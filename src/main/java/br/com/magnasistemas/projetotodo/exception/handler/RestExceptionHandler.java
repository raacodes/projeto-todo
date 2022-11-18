package br.com.magnasistemas.projetotodo.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.magnasistemas.projetotodo.exception.BadRequestDetails;
import br.com.magnasistemas.projetotodo.exception.BadRequestException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestDetails> handlerBadRequestException(BadRequestException bad) {
		BadRequestDetails bd = new BadRequestDetails();
		bd.setStatus(HttpStatus.BAD_REQUEST.value());
		bd.setDetails(bad.getMessage());
		bd.setDeveloperMessage(bad.getClass().getName());
		bd.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(bd, HttpStatus.BAD_REQUEST);

	}

}
