package com.epam.learn.java.ad.gallery.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.learn.java.ad.gallery.api.ServiceProviderI;
import com.epam.learn.java.ad.gallery.app.ApplicationContext;
import com.epam.learn.java.ad.gallery.app.ServiceProvider;

/**
 * subclasses responsible for transform request parameters to service arguments, 
 * passing response data to view, determine witch view to show
 * 
 * Assume this is a border of web layer
 * 
 * @author Administrator
 *
 */
public abstract class WebCommand {
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private String viewPath;
	private boolean redirect;
	protected ServiceProviderI serviceProvider;
	protected ApplicationContext appContext; 

	public WebCommand init(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;
		
		this.serviceProvider = new ServiceProvider(getAppContext());
		return this;
	}

	public void run() throws ServletException, IOException {

		if (!serviceProvider.getSecurityService().checkAccess(this.getClass())) {
			forward("/view/401.jsp");
			return;
		}

		try {
			process();
			addEssentials();
			showResult();
		} catch (Exception e) {
			forward("/view/500.jsp");
			e.printStackTrace();
		}
	}

	
	protected void addEssentials() {
		request.setAttribute("user", appContext.getUser());
	}

	private void showResult() throws IOException, ServletException {
		if (redirect) {
			response.sendRedirect(request.getContextPath() + viewPath);
			return;
		}
		forward("/view/" + viewPath + ".jsp");
	}

	protected void addAppContextValiables(HttpServletRequest request) {
		request.setAttribute("app", getAppContext());
	}

	protected abstract void process() throws Exception;

	protected ApplicationContext getAppContext() {
		if (appContext == null ) {
			appContext  = WebCommand.readContext(request);	
		}
		return appContext; 
	}

	protected void setName(String name) {
		this.viewPath = name;
	}

	protected void redirect(String string) {
		viewPath = string;
		redirect = true;
	}

	protected void forward(String path) throws ServletException, IOException {
		addAppContextValiables(request);
		context.getRequestDispatcher(path).forward(request, response);
	}

	protected void forward(Class<? extends WebCommand> class1) {
		viewPath = class1.getCanonicalName().substring(WebCommand.class.getPackageName().length() + 1).replace('.',
				'/');
	}

	protected void redirect(Class<? extends WebCommand> class1, String params) {
		StringBuilder path = new StringBuilder();
		path.append("/").append(
				class1.getCanonicalName().substring(WebCommand.class.getPackageName().length() + 1).replace('.', '/'));
		if (params != null) {
			path.append("?").append(params);
		}
		redirect(path.toString());
	}

	protected void redirect(Class<? extends WebCommand> class0) {
		redirect(class0, null);
	}

	public static ApplicationContext readContext(HttpServletRequest request) {
		final String contextAttributeName = "ApplicationContext";
		HttpSession session = request.getSession(true);
		ApplicationContext context = (ApplicationContext) session.getAttribute(contextAttributeName);
		if (context == null) {
			context = new ApplicationContext();
			session.setAttribute(contextAttributeName, context);
		}
		return context;
	}

}