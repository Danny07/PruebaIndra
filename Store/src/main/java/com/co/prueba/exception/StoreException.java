package com.co.prueba.exception;

public class StoreException extends Exception {

	private static final long serialVersionUID = -485538808650509569L;

	public StoreException(String message, String error) {
		super(String.format(message, error));
	}
}
