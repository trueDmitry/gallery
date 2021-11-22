package com.epam.learn.java.ad.gallery.api.db;

import java.util.List;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;

public interface ExpositionOrderDaoI extends BaseDaoI<ExpositionTicket>{

	Optional<ExpositionTicket> find(int expoId, int userId) throws DBProblemException ;

	List<ExpositionTicket> selectForUser(int userId) throws DBProblemException;

	
}
