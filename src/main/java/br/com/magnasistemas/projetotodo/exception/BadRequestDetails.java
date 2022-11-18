package br.com.magnasistemas.projetotodo.exception;

import java.time.LocalDateTime;

public class BadRequestDetails {
	private int status;
	private String details;
	private String developerMessage;
	private LocalDateTime timestamp;

	public BadRequestDetails() {
	}

	public BadRequestDetails(int status, String details, String developerMessage, LocalDateTime timestamp) {
		this.status = status;
		this.details = details;
		this.developerMessage = developerMessage;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
