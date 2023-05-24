package com.exporter.exceptions;

public class FileTypeException extends RuntimeException {

	private static final long serialVersionUID = -9137615972141829644L;

	
	private String message;
	
	/**
	 * Default Constructor
	 */
	public FileTypeException() {
		
	}

	/**
	 * @param message
	 */
	public FileTypeException(String message) {
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
		return "FileTypeException: " + message;
	}
	
	
	
}
