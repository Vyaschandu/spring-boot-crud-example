package com.example.RestCrudDemo.error;

public class RestControllerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CodeError codeerror;

	public RestControllerException(CodeError coreerror) {
		super(coreerror.getMessage());
		this.codeerror = coreerror;
	}

	public RestControllerException(CodeError codeerror, String message) {
		super(composeMessage(codeerror));
		this.codeerror = codeerror;
	}

	public RestControllerException(CodeError codeerror, Throwable cause) {
		super(codeerror.getMessage(), cause);
		this.codeerror = codeerror;
	}

	public static String composeMessage(CodeError codeerror) {
		return codeerror.getMessage();

	}

	public CodeError getCodeerror() {
		return codeerror;
	}

	public void setCoreerror(CodeError codeerror) {
		this.codeerror = codeerror;
	}

}
