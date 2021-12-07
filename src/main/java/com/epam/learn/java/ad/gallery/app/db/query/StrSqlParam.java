package com.epam.learn.java.ad.gallery.app.db.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

class StrSqlParam extends SqlParam<String>{
	@Override
	public void putData(PreparedStatement ps, int serial) throws SQLException {
		ps.setString(serial, data);
	}

	@Override
	public void setData(String str) {
		this.data = str;
	}
	
}