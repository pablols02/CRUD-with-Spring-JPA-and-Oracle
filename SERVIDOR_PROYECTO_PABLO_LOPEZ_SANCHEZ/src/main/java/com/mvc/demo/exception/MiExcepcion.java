package com.mvc.demo.exception;

public class MiExcepcion extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	
	public MiExcepcion(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
