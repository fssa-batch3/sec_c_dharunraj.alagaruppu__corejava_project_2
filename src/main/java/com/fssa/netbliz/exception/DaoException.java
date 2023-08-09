package com.fssa.netbliz.exception;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(Throwable te) {
		super(te);
	}

	public DaoException(String msg) {
		super(msg);
	}

	public DaoException(Throwable te, String msg) {
		super(msg, te);
	}
}
