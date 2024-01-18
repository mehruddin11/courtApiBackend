package com.nagarro.ProductSearchApi.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5121926031460552918L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
