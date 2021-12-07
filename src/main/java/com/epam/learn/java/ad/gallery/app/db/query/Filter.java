package com.epam.learn.java.ad.gallery.app.db.query;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	List<Condition> conditions = new ArrayList<>();
	private TableConfig config;

	public Filter(TableConfig config) {
		this.config = config;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	private void put(String field, String op, SqlParam<?> param) {
		conditions.add(new Condition(field, op, param));
	}


	public void put(String field, String op, String data) {
		SqlParam<?> param = config.createFieldParam(field);
		param.setData(data);
		put(field, op, param);
	}

	public void put(String field, String op, Integer data) {
		SqlParam<?> param = config.createFieldParam(field);
		if (param instanceof IntSqlParam) {
			((IntSqlParam)param).setData(data);
		} else {
			param.setData(data.toString());
		}
		put(field, op, param);
	}
	
}