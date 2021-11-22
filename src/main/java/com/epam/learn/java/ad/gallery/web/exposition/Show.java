package com.epam.learn.java.ad.gallery.web.exposition;

import com.epam.learn.java.ad.gallery.web.WebCommand;

public class Show extends WebCommand{

	@Override
	protected void process() throws Exception {
		int expoId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("expoView", serviceProvider.getViewService().getExpositionView(expoId));
	}

}
