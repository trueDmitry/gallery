package com.epam.learn.java.ad.gallery.web;

public enum HttpCode {

	OK(200),
	UNAUTHORIZED(401),
	UNPROCESSABLE_ENTRY(422),
	INTERNAL_SERVER_ERROR(500);
	
	HttpCode(int i) {
		code = i;
	}

	private int code;

	public int getCode() {
		return code;
	}

}
