package com.epam.learn.java.ad.gallery.api.db;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.ExpositionRoom;

public interface ExpositionRoomDaoI extends BaseDaoI<ExpositionRoom>{

	void deleteByExpositionId(int id) throws DBProblemException;

}
