package com.epam.learn.java.ad.gallery.app.db.query;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TableConfig {
	Map<String, Supplier<SqlParam<?>>> types = new HashMap<>();

	public void asInt(String field) {
		types.put(field, IntSqlParam::new);
	}

	public SqlParam<?> createFieldParam(String field) {
		return types.getOrDefault(field, StrSqlParam::new).get();
	}
}
