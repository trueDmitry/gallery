package com.epam.learn.java.ad.gallery.api;

import java.util.List;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.db.FilterI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;

public interface ExpositionServiceI {

	List<ExpositionTicket> getExpositionTickets(int id) throws DBProblemException;

	Optional<ExpositionTicket> getTicketByExposition(int expoId);

	boolean store(Exposition expo) throws DBProblemException;

	void delete(int id) throws DBProblemException;

}
