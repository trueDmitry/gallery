package com.epam.learn.java.ad.gallery.app.db.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ParamList {

	int serialCounter = 0;
	private List<SqlParam<?>> params = new ArrayList<>();

	public void putData(PreparedStatement ps) throws SQLException {
		int serial = 0;
		for (SqlParam<?> filler : params) {
			filler.putData(ps, ++serial);
		}
	}

	public void add(SqlParam<?> param ) {
		params.add(param);
	}
	
}