package com.nagarro.ProductSearchApi.exception;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6262251551542920700L;

	public InvalidCredentialsException(String message) {
        super(message);
    }
}
