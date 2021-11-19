package com.epam.learn.java.ad.gallery.api;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.model.Exposition;
import com.epam.learn.java.ad.gallery.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.web.view.ExpositionEditView;
import com.epam.learn.java.ad.gallery.web.view.ExpositionView;

public interface ExpositionServiceI {

	List<Exposition> getExpositions() throws SQLException;

	List<ExpositionTicket> getExpositionTickets(int id) throws DBProblemException;

	Optional<ExpositionTicket> getTicketByExposition(int expoId);

	ExpositionView getExpositionView(int expoId) throws DBProblemException;

	boolean store(Exposition expo) throws DBProblemException;

	ExpositionEditView getExpositionEdit(int id) throws DBProblemException;

	void delete(int id) throws DBProblemException;

}
