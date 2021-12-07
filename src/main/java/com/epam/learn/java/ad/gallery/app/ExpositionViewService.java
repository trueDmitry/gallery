package com.epam.learn.java.ad.gallery.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.learn.java.ad.gallery.api.AbstractService;
import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.api.ExpositionViewServiceI;
import com.epam.learn.java.ad.gallery.api.SecurityServiceI;
import com.epam.learn.java.ad.gallery.api.ServiceProviderI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.api.db.RoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.FilterI;
import com.epam.learn.java.ad.gallery.api.model.paging.PaginSourceI;
import com.epam.learn.java.ad.gallery.api.model.paging.Paginator;
import com.epam.learn.java.ad.gallery.api.model.paging.PagingException;
import com.epam.learn.java.ad.gallery.app.db.AndFilter;
import com.epam.learn.java.ad.gallery.app.db.ExpositionDao;
import com.epam.learn.java.ad.gallery.app.db.query.Filter;
import com.epam.learn.java.ad.gallery.app.db.query.TableConfig;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.exposition.ExpositionViewPagingSource;
import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.app.model.User;
import com.epam.learn.java.ad.gallery.web.model.ExpositionAllView;
import com.epam.learn.java.ad.gallery.web.model.ExpositionEditView;
import com.epam.learn.java.ad.gallery.web.model.ExpositionView;

public class ExpositionViewService extends AbstractService implements ExpositionViewServiceI {

	int DEFAULT_ITEMS_ON_PAGE = 2;

	private ApplicationContext context;
	private DatabaseServiceI db;
	private SecurityServiceI securityService;

	public ExpositionViewService(ServiceProviderI sp, ApplicationContext context) {
		super(sp);
		db= getServiceProvider().getDatabaseService();
		securityService = getServiceProvider().getSecurityService();
		this.context = context;
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
	public ExpositionAllView getExpositionsView(int page) throws DBProblemException {
		int quantity = DEFAULT_ITEMS_ON_PAGE;

		
		TableConfig config = new TableConfig();
		config.asInt("published");
		
		Filter filter = new Filter(config);	
		if (!securityService.userHasRole("admin")) {
			filter.put("published", "=", 1);
		}
		
		ExpositionViewPagingSource ps = new ExpositionViewPagingSource(filter, getServiceProvider() );

		Paginator<ExpositionView> paginator = new Paginator<>(ps, page, quantity);

		ExpositionAllView expoAllView = new ExpositionAllView();
		expoAllView.setPaginator(paginator);
		return expoAllView;
	}


}
