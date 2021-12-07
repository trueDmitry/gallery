package com.epam.learn.java.ad.gallery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.api.SecurityServiceI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionRoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.FilterI;
import com.epam.learn.java.ad.gallery.app.db.ExpositionDao;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.ExpositionRoom;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;

public class ExpositionService implements ExpositionServiceI {

	private SecurityServiceI securityService;
	private DatabaseServiceI db;
	private ApplicationContext context;
	
	protected static Logger logger = LogManager.getLogger();

	public ExpositionService(ApplicationContext context, SecurityServiceI securityService,
			DatabaseServiceI databaseService) {
		this.securityService = securityService;
		this.db = databaseService;
		this.context = context;
		
//		logger.trace("test trace not showing");
//		logger.debug("test debug");
	}

	public boolean store(Exposition expo) throws DBProblemException {
		if (expo == null) {
			return false;
		}

		try (ExpositionDaoI expoDao = db.getExpositionDao();
				ExpositionRoomDaoI expoRoomDao = db.getExpositionRoomDao()) {
			db.startTransaction();
			if (expo.getId() == 0) {
				expoDao.insert(expo);
				expoRoomDao.insert(expoRoomsList(expo));
			} else {
				expoDao.update(expo);
				expoRoomDao.deleteByExpositionId(expo.getId());
				expoRoomDao.insert(expoRoomsList(expo));
			}
			db.endTransaction();
		}

		return true;
	}

	private static List<ExpositionRoom> expoRoomsList(Exposition expo) {
		return expo.getRooms().stream().map(room -> new ExpositionRoom(expo.getId(), room.getId()))
				.collect(Collectors.toList());
	}

	/**
	 * must be replaced with filter and paging
	 * @return
	 * @throws DBProblemException
	 */
	@Deprecated
	public List<Exposition> getExpositions() throws DBProblemException {
		try {
			if (securityService.userHasRole("admin")) {
				return ExpositionDao.getExpositions();
			}
			return ExpositionDao.getPublishedExpositions();
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
	}

	public List<ExpositionTicket> getExpositionTickets(int userId) throws DBProblemException {
		try (ExpositionOrderDaoI orderDao = db.getExpositionOrder()) {
			return orderDao.selectForUser(userId);
		}
	}

	public Optional<ExpositionTicket> getTicketByExposition(int expoId) {
		return Optional.empty();
	}

	@Override
	public void delete(int id) throws DBProblemException {
		try (ExpositionDaoI expoDao = db.getExpositionDao()) {
			expoDao.delete(id);
		}
	}

//	@Override
//	public List<Exposition> getExpositions(int startIndex, int quantity, FilterI filter)
//			throws DBProblemException {
//		
//		if (securityService.userHasRole("admin")) {
//			filter.and("published", "1");
//		}
//		
//		try (ExpositionDaoI expoDao = db.getExpositionDao()) {
//			return expoDao.get(startIndex, quantity, filter);
//		}
//	}

}
