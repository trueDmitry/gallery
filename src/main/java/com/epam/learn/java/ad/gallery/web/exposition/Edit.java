package com.epam.learn.java.ad.gallery.web.exposition;

import com.epam.learn.java.ad.gallery.web.RequestHelper;
import com.epam.learn.java.ad.gallery.web.WebCommand;

public class Edit extends WebCommand{

	@Override
	protected void process() throws Exception {
		int id = new RequestHelper(request).getId("id");
		request.setAttribute("expoEditView", serviceProvider.getViewService().getExpositionEdit(id));
	}
}
