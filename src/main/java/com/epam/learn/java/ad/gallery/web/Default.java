package com.epam.learn.java.ad.gallery.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.epam.learn.java.ad.gallery.app.model.User;

public class Default extends WebCommand {

 	@Override
	public void process() throws ServletException, IOException {
 		User user = getAppContext().getUser();
 		request.setAttribute("user", user);
 		
	}

}
