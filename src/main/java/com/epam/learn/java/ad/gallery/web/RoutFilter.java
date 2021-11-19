package com.epam.learn.java.ad.gallery.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RoutFilter  implements Filter  {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(req.getContextPath().length());

		if (path.startsWith("/view")) {
		    chain.doFilter(request, response); 
		} else if (path.indexOf('.') > path.indexOf('/')){
			request.getRequestDispatcher("/view" + path).forward(request, response);
		} else {
			request.setAttribute("originalPath", path);
			request.getRequestDispatcher("/web" + path).forward(request, response);
		}
		
	}

}
