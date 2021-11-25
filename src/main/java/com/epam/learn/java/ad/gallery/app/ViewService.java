package com.epam.learn.java.ad.gallery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.api.ViewServiceI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.api.db.RoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.FilterI;
import com.epam.learn.java.ad.gallery.api.model.paging.PaginSourceI;
import com.epam.learn.java.ad.gallery.api.model.paging.Paginator;
import com.epam.learn.java.ad.gallery.app.db.AndFilter;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.app.model.User;
import com.epam.learn.java.ad.gallery.web.model.ExpositionAllView;
import com.epam.learn.java.ad.gallery.web.model.ExpositionEditView;
import com.epam.learn.java.ad.gallery.web.model.ExpositionView;

public class ViewService implements ViewServiceI {

	private ApplicationContext context;
	private DatabaseServiceI db;
	private ExpositionServiceI expoServ;

	public ViewService(ApplicationContext context, DatabaseServiceI databaseService,
			ExpositionServiceI expositionService) {
		this.context = context;
		this.db = databaseService;
		this.expoServ = expositionService;
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
	public ExpositionAllView getExpoViews(int page) throws DBProblemException {
		int quantity = 2;

		// TODO migrate to expoServ
		FilterI filter = new AndFilter();

		ExpositionViewPagingSource ps = new ExpositionViewPagingSource(filter);

		Paginator<ExpositionView> paginator = new Paginator<>(ps, page, quantity);

		ExpositionAllView expoAllView = new ExpositionAllView();
		expoAllView.setPaginator(paginator);
		return expoAllView;
	}

	/**
	 * 
	 * @return map of user tickets by exposition id
	 * @throws DBProblemException
	 */
	private Map<Integer, ExpositionTicket> getUserTickets() throws DBProblemException {
		User user = context.getUser();
		List<ExpositionTicket> userTickets = new ArrayList<ExpositionTicket>();
		if (user != null) {
			userTickets = expoServ.getExpositionTickets(context.getUser().getId());
		}

		Map<Integer, ExpositionTicket> orderMap = userTickets.stream()
				.collect(Collectors.toMap(ExpositionTicket::getExpositionId, v -> v));
		return orderMap;
	}

	class ExpositionViewPagingSource implements PaginSourceI<ExpositionView> {

		private FilterI filter;

		public ExpositionViewPagingSource(FilterI filter) {
			this.filter = filter;
		}

		@Override
		public List<ExpositionView> fetchData(int startIndex, int quantity) {
			List<ExpositionView> expoViews = new ArrayList<>();
			
			try (ExpositionDaoI expoDao = db.getExpositionDao()) {
				List<Exposition> expos = expoDao.get(startIndex, quantity, filter);
				Map<Integer, ExpositionTicket> ticketMap = getUserTickets();

				expoViews = expos.stream().map(expo -> {
					ExpositionView ev = new ExpositionView();
					ev.setExposition(expo);
					ev.setTicketBought(ticketMap.get(expo.getId()) != null);
					return ev;
				}).collect(Collectors.toList());

			} catch (DBProblemException e) {
				e.printStackTrace();
			}
			return expoViews;
		}

		@Override
		public int countTotal() {
			try (ExpositionDaoI expoDao = db.getExpositionDao()) {
				return expoDao.count(filter);
			} catch (DBProblemException e) {
				e.printStackTrace();
			}
			return 0;
		}

	}

}
