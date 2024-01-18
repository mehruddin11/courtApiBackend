package com.nagarro.ProductSearchApi.exception;

public class DuplicatePhoneNumberException extends RuntimeException {
	 
	private static final long serialVersionUID = 2440834209657815117L;

	private int nextStep;
    private String message;
    Long userId;
    
	public DuplicatePhoneNumberException(String message, int nextStep,Long userId) {
		super(message);
		this.message= message;
		this.nextStep=nextStep;
		this.userId= userId;
	}
	
	

	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public int getNextStep() {
		return nextStep;
	}

	public void setNextStep(int nextStep) {
		this.nextStep = nextStep;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
