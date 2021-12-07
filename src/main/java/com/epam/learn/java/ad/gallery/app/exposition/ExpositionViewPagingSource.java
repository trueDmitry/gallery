package com.epam.learn.java.ad.gallery.app.exposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.ServiceProviderI;
import com.epam.learn.java.ad.gallery.api.UserServiceI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.model.paging.PaginSourceI;
import com.epam.learn.java.ad.gallery.api.model.paging.PagingException;
import com.epam.learn.java.ad.gallery.app.db.query.Filter;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.web.model.ExpositionView;

public class ExpositionViewPagingSource implements PaginSourceI<ExpositionView> {
	private DatabaseServiceI db;
	protected Filter filter;
	private UserServiceI userService;
	ServiceProviderI sp;

	public ExpositionViewPagingSource(Filter filter, ServiceProviderI sp) {
		this.db = sp.getDatabaseService();		
		this.userService =  sp.getUserService();
		this.filter = filter;
	}
	
	protected Filter getFilter() {
		return filter;
	}

	@Override
	public List<ExpositionView> fetchData(int startIndex, int quantity) throws PagingException {
		List<ExpositionView> expoViews = new ArrayList<>();

		try (ExpositionDaoI expoDao = db.getExpositionDao()) {

			List<Exposition> expos = expoDao.get(startIndex, quantity, getFilter());

			expoViews = toExpoViews(expos);
			setTicketBoughtFlag(expoViews);

		} catch (DBProblemException e) {
			throw new PagingException(e);
		}
		return expoViews;
	}

	private void setTicketBoughtFlag(List<ExpositionView> expoViews) throws DBProblemException {
		Map<Integer, ExpositionTicket> ticketMap = userService.getUserTickets();
		expoViews.forEach(ev -> {
			if (ticketMap.containsKey(ev.getExposition().getId())) {
				ev.setTicketBought(true);
			}
		});
	}

	private List<ExpositionView> toExpoViews(List<Exposition> expos) {
		return expos.stream().map(expo -> {
			ExpositionView ev = new ExpositionView();
			ev.setExposition(expo);
			return ev;
		}).collect(Collectors.toList());
	}

	@Override
	public int countTotal() throws PagingException {
		try (ExpositionDaoI expoDao = db.getExpositionDao()) {
			return expoDao.count(getFilter());
		} catch (DBProblemException e) {
			throw new PagingException(e);
		}
	}


}
