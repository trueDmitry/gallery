package com.epam.learn.java.ad.gallery.api;

import java.util.List;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.web.model.ExpositionAllView;
import com.epam.learn.java.ad.gallery.web.model.ExpositionEditView;
import com.epam.learn.java.ad.gallery.web.model.ExpositionView;

/**
 * prepare data to presentation
 * @author Administrator
 *
 */
public interface ExpositionViewServiceI {

	ExpositionView getExpositionView(int expoId) throws DBProblemException;

	ExpositionEditView getExpositionEdit(int id) throws DBProblemException;

	ExpositionAllView getExpositionsView(int page) throws Exception;

}
