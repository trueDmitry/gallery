package com.epam.learn.java.ad.gallery.api.db;

import java.util.List;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;


/**
 * 
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseDaoI <T> extends AutoCloseable {
	
	Optional<T> get(int expoId) throws DBProblemException;
	
	void insert(T order) throws DBProblemException;
	
	void insert(List<T> l) throws DBProblemException;
	
	void update(T o) throws DBProblemException;

	@Override
	void close() throws DBProblemException;
	
	List<T> getAll() throws DBProblemException;
	
	void delete(int id) throws DBProblemException;

}
