package com.example.RestCrudDemo.error;

public class HandleException extends RestControllerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HandleException(CodeError codeerror) {
		super(codeerror);
	}

	public HandleException(CodeError codeerror, Throwable cause) {
		super(codeerror, cause);
	}

}
