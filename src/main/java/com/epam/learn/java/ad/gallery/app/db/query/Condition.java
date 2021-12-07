package com.epam.learn.java.ad.gallery.app.db.query;

public class Condition {
	String field;
	String op;
	SqlParam<?> data;
	
	public Condition(String field, String op, SqlParam<?> data) {
		this.field = field;
		this.op = op;
		this.data = data;
	}

	public SqlParam <?> getData() {
		return data;
	}

	public String getOperation() {
		return op;
	}

	public String getField() {
		return field;
	}
}