package com.epam.learn.java.ad.gallery.web;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.epam.learn.java.ad.gallery.app.ApplicationContext;
import com.epam.learn.java.ad.gallery.app.SecurityService;

public class Login extends WebCommand {

	public void process() throws Exception {
		if(getAppContext().getUser() != null ) {
			request.getRequestDispatcher("/").forward(request, response);	
		}
	}
}
