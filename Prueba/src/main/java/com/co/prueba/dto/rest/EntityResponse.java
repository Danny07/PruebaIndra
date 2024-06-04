package com.co.prueba.dto.rest;

public class EntityResponse<T> {
	
	private T body;
	
	private String message;

	public EntityResponse(T body, String message) {
		this.body = body;
		this.message = message;
	}

	public EntityResponse() {
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
