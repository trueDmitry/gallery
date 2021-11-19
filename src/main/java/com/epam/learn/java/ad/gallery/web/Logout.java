package com.epam.learn.java.ad.gallery.web;

import com.epam.learn.java.ad.gallery.app.SecurityService;

public class Logout extends WebCommand{

	@Override
	protected void process() throws Exception {
		// TODO decouple / add session id to app context
		// ServiceProvider.getAuthentication(getAppContext());
		new SecurityService(getAppContext()).logout();
		forward(Default.class);
	}

}
