package com.epam.learn.java.ad.gallery.app.db.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IntSqlParam extends SqlParam<Integer>{
	@Override
	public void putData(PreparedStatement ps, int serial) throws SQLException {
		ps.setInt(serial, data);
	}

	@Override
	public void setData(String str) {
		this.data = Integer.valueOf(str);
	}
}