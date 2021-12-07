package com.epam.learn.java.ad.gallery.api;

import java.util.Map;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;

public interface UserServiceI {

	/**
	 * 
	 * @return map of user tickets by exposition id
	 * @throws DBProblemException
	 */
	Map<Integer, ExpositionTicket> getUserTickets() throws DBProblemException;

}
