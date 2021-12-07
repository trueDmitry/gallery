package com.epam.learn.java.ad.gallery.app.db.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueryBuilder {
	
	// simple "and" conditions
	List<Condition> conditions = new ArrayList<>();
	
	StringBuilder sBuilder;

	private String fields;

	private String from;

	private StringBuilder limit;

	/**
	 * stores list of parameters to post into PreparedStatement 
	 */
	ParamList params;

	protected static Logger logger = LogManager.getLogger();
	

	public String getQuery() {
		params = new ParamList();

		sBuilder = new StringBuilder();
		sBuilder.append(" SELECT ").append(fields);
		sBuilder.append(" FROM ").append(from);
		
		if (conditions.size() > 0) {
			sBuilder.append(" WHERE ");
			commitCondition2Query(conditions.get(0));
			for (int i = 1; i < conditions.size(); i++) {
				sBuilder.append(" AND ");
				commitCondition2Query(conditions.get(i));
			}
		}
		
		if (limit != null ) {
			sBuilder.append(limit);
		}
		
		
		return sBuilder.toString();
	}

	private void commitCondition2Query(Condition predicate) {
		sBuilder.append(String.format("%s %s ?", predicate.getField(), predicate.getOperation()));
		params.add(predicate.getData());	
	}

	public void populate(PreparedStatement ps) throws SQLException {
		params.putData(ps);
	}

	public void select(String fields) {
		this.fields = fields;
	}

	public void from(String from) {
		this.from = from;
	}

	public void limit(int startIndex, int quantity) {
		this.limit = new StringBuilder(" LIMIT ").append(startIndex).append(",").append(quantity);
	}

	public void where(Filter filter) {
		conditions = filter.getConditions();
	}
	
}
