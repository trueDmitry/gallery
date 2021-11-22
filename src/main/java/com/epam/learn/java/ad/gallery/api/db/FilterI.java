package com.epam.learn.java.ad.gallery.api.db;

public interface FilterI {
	String getWhereClause();

	void and(String param, String value);
}
