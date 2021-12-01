package com.epam.learn.java.ad.gallery.app;


import java.util.HashMap;
import java.util.Map;

import com.epam.learn.java.ad.gallery.api.SecurityServiceI;
import com.epam.learn.java.ad.gallery.app.db.UserDao;
import com.epam.learn.java.ad.gallery.app.exception.AuthenticationException;
import com.epam.learn.java.ad.gallery.app.model.User;
import com.epam.learn.java.ad.gallery.web.WebCommand;
import com.epam.learn.java.ad.gallery.web.exposition.Acquire;
import com.epam.learn.java.ad.gallery.web.exposition.Delete;
import com.epam.learn.java.ad.gallery.web.exposition.Edit;
import com.epam.learn.java.ad.gallery.web.exposition.Save;

public class SecurityService implements SecurityServiceI {
	
	private ApplicationContext appContext;

	public SecurityService(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public boolean authenticate(String name, String password) throws AuthenticationException {
		
		User user = appContext.getUser();
		if (user != null ) {
			return true;
		}
		if (name == null || password == null) {
			return false;
		}
		try {
			user = UserDao.getUser(name, password);
			appContext.setUser(user);
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
		return user != null;
	}

	public void logout() {
		appContext.setUser(null);
	}
	

	/**
	 * 
	 * @param classO
	 * @return true if no list
	 */
	public boolean checkAccess(Class<? extends WebCommand> classO) {
		String role = getAuthorities().get(classO);
		if (role == null) {
			return true;
		}
		return userHasRole(role);
	}


	private Map<Class<? extends WebCommand>, String> getAuthorities() {
		Map<Class<? extends WebCommand>, String> map = new HashMap<>();
		map.put(Edit.class, "admin");
		map.put(Delete.class, "admin");
		map.put(Save.class, "admin");
		map.put(Acquire.class, "authenticated");
		return map;
	}

	public boolean userHasRole(String role) {
		User user = appContext.getUser();
		if (user == null) {
			return false;
		}
		return role.equals("authenticated") || user.getRoles().contains(role);
	}

}
