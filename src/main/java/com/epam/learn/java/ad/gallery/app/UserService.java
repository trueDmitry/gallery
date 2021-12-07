package com.epam.learn.java.ad.gallery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.api.UserServiceI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.app.model.User;

public class UserService implements UserServiceI{
	private ApplicationContext context;
	private ExpositionServiceI expoServ;
	
	
	public UserService (ApplicationContext context, ExpositionServiceI expositionService) {
		this.context = context;
		this.expoServ = expositionService;
		
	}

	/**
	 * 
	 * @return map of user tickets by exposition id
	 * @throws DBProblemException
	 */
	@Override
	public Map<Integer, ExpositionTicket> getUserTickets() throws DBProblemException {
		User user = context.getUser();
		List<ExpositionTicket> userTickets = new ArrayList<ExpositionTicket>();
		if (user != null) {
			userTickets = expoServ.getExpositionTickets(context.getUser().getId());
		}

		Map<Integer, ExpositionTicket> orderMap = userTickets.stream()
				.collect(Collectors.toMap(ExpositionTicket::getExpositionId, v -> v));
		return orderMap;
	}
	
}
