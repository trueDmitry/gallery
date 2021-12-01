package com.epam.learn.java.ad.gallery.app.exception;

import java.sql.SQLException;

public class DBProblemException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 237521035573750317L;

	public DBProblemException(Exception e) {
		super(e);
	}

	public DBProblemException() {
		super();
	}

}
