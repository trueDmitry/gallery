package com.epam.learn.java.ad.gallery.web;

import com.epam.learn.java.ad.gallery.api.SecurityServiceI;
import com.epam.learn.java.ad.gallery.app.ApplicationContext;
		     
public class Authenticate extends WebCommand {

	public void process() throws Exception {

		String name = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");

		ApplicationContext appContext = getAppContext();
		
		boolean isAuthentificated = appContext.getUser() != null;  
		if (!isAuthentificated) {
			SecurityServiceI ss = serviceProvider.getSecurityService();
			isAuthentificated =  ss.authenticate(name, password);
		}
		
		request.setAttribute("authentificated", isAuthentificated);
		request.setAttribute("user", appContext.getUser());
		
		if (isAuthentificated) {
			redirect("/");
		} else {
			forward(Login.class);
		}
	}
}
