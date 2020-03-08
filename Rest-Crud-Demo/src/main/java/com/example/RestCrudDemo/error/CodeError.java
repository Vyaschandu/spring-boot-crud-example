package com.example.RestCrudDemo.error;

public enum CodeError {

	DATA_NOT_FOUND("404", "data not found"), SERVOR_ERROR("500", "server error");

	private final String message;
	private final String code;

	CodeError(String code, final String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
