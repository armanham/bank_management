package com.exporter.exceptions;

public class InjuryFileException extends RuntimeException{
	
	private static final long serialVersionUID = -1601449101211665811L;
	
	private String message;
	
	/**
	 * Default Constructor
	 */
	public InjuryFileException() {
		
	}

	/**
	 * @param message
	 */
	public InjuryFileException(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "InjuryFileException: " + message;
	}
	
	
}
