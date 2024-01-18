package com.nagarro.ProductSearchApi.exception;

public class DuplicateEmailException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nextStep;
    private Long userId; // Add user id as a field

    public DuplicateEmailException(String message, int nextStep, Long userId) {
        super(message);
        this.nextStep = nextStep;
        this.userId = userId;
    }
    

    // Add getters for nextStep and userId

    public int getNextStep() {
        return nextStep;
    }

    public void setNextStep(int nextStep) {
        this.nextStep = nextStep;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

