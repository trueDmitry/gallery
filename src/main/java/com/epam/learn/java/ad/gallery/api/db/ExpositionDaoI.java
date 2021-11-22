package com.epam.learn.java.ad.gallery.api.db;

import java.util.List;

import com.epam.learn.java.ad.gallery.api.model.paging.PaginSourceI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;

public interface ExpositionDaoI extends BaseDaoI<Exposition> {

	List<Exposition> get(int startIndex, int quantity, FilterI filter) throws DBProblemException;

	int count(FilterI filter) throws DBProblemException;


}
