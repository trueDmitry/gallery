package com.epam.learn.java.ad.gallery.app.db.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SqlParam<T> {
	protected T data;
	abstract public void putData(PreparedStatement ps, int serial) throws SQLException;
	public abstract void setData(String str);

	public void setData(T data) {
		this.data = data;
	}
}