package com.epam.learn.java.ad.gallery.api.db;

import java.util.List;

import com.epam.learn.java.ad.gallery.app.db.query.Filter;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;

public interface ExpositionDaoI extends BaseDaoI<Exposition> {

	List<Exposition> get(int startIndex, int quantity, Filter filter) throws DBProblemException;

	int count(Filter filter) throws DBProblemException;


}
