package com.epam.learn.java.ad.gallery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.api.SecurityServiceI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionRoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.RoomDaoI;
import com.epam.learn.java.ad.gallery.app.db.ExpositionDao;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.model.Exposition;
import com.epam.learn.java.ad.gallery.model.ExpositionRoom;
import com.epam.learn.java.ad.gallery.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.web.view.ExpositionEditView;
import com.epam.learn.java.ad.gallery.web.view.ExpositionView;

public class ExpositionService implements ExpositionServiceI {

	private SecurityServiceI securityService;
	private DatabaseServiceI db;
	private ApplicationContext context;

	public ExpositionService(ApplicationContext context, SecurityServiceI securityService,
			DatabaseServiceI databaseService) {
		this.securityService = securityService;
		this.db = databaseService;
		this.context = context;
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

	public List<Exposition> getExpositions() throws SQLException {
		if (securityService.userHasRole("admin")) {
			return ExpositionDao.getExpositions();
		}
		return ExpositionDao.getPublishedExpositions();
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
	public ExpositionView getExpositionView(int expoId) throws DBProblemException {
		ExpositionView expoView = new ExpositionView();

		try (ExpositionDaoI expoDao = db.getExpositionDao(); ExpositionOrderDaoI orderDao = db.getExpositionOrder()) {
			expoView.setExposition(expoDao.get(expoId).get());
			if (context.getUser() != null) {
				expoView.setTicketBought(orderDao.find(expoId, context.getUser().getId()).isPresent());
			}
		}

		return expoView;
	}

	@Override
	public ExpositionEditView getExpositionEdit(int expoId) throws DBProblemException {
		ExpositionEditView expoEdit = new ExpositionEditView();
		Optional<Exposition> expoOption;
		try (ExpositionDaoI expoDao = db.getExpositionDao()) {
			expoOption = expoDao.get(expoId);
		}
		expoOption.ifPresentOrElse(expoEdit::setExpo, () -> expoEdit.setExpo(new Exposition()));

		try (RoomDaoI roomDao = db.getRoomDao()) {
			expoEdit.setRooms(roomDao.getAll());
		}

		return expoEdit;
	}

	@Override
	public void delete(int id) throws DBProblemException {
		try(ExpositionDaoI expoDao = db.getExpositionDao()){
			expoDao.delete(id);
		}
	}

}
