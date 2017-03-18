package com.andrewpanasyuk.dao;

@SuppressWarnings("serial")
public class DAOExeption extends Exception {

	public DAOExeption() {
		super();
	}

	public DAOExeption(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DAOExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOExeption(String message) {
		super(message);
	}

	public DAOExeption(Throwable cause) {
		super(cause);
	}

	
}
