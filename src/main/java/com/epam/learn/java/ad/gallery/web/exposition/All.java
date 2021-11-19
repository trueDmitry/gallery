package com.epam.learn.java.ad.gallery.web.exposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.model.Exposition;
import com.epam.learn.java.ad.gallery.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.model.User;
import com.epam.learn.java.ad.gallery.web.WebCommand;
import com.epam.learn.java.ad.gallery.web.view.ExpositionView;

public class All extends WebCommand {

	@Override
	protected void process() throws Exception {

		ExpositionServiceI expoServ = serviceProvider.getExpositionService();
		List<Exposition> expos = expoServ.getExpositions();

		User user = appContext.getUser(); 
		List<ExpositionTicket> userTickets = new ArrayList<ExpositionTicket>();	
		if (user != null) {
			userTickets = expoServ.getExpositionTickets(appContext.getUser().getId());
		}

		Map<Integer, ExpositionTicket> orderMap = userTickets.stream()
				.collect(Collectors.toMap(ExpositionTicket::getExpositionId, v -> v));

		List<ExpositionView> expoViews = expos.stream().map(v -> {
			ExpositionView ev = new ExpositionView();
			ev.setExposition(v);
			ev.setTicketBought(orderMap.get(v.getId()) != null);
			return ev;
		}).collect(Collectors.toList());

		request.setAttribute("expositions", expoViews);
	}

}
