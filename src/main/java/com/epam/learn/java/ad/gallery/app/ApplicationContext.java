package com.epam.learn.java.ad.gallery.app;

import java.io.Serializable;

import com.epam.learn.java.ad.gallery.model.User;

public class ApplicationContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
