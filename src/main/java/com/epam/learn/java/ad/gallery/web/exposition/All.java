package com.epam.learn.java.ad.gallery.web.exposition;

import com.epam.learn.java.ad.gallery.web.RequestHelper;
import com.epam.learn.java.ad.gallery.web.WebCommand;

public class All extends WebCommand {

	@Override
	protected void process() throws Exception {
//		request.setAttribute("expositions", serviceProvider.getViewService().getExpoViewsAll());
		int page = new RequestHelper(request).getId("page");
		
		request.setAttribute("data", serviceProvider.getViewService().getExpoViews(page));
	}

}
