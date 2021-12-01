package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

	private ConnectionPool() {
		// private constructor
	}

	private static ConnectionPool instance;
	
	public static Connection getConnection() {
		return ConnectionPool.getInstance().pullConnection();
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			} 
		}
		return instance;
	}

	private Connection pullConnection() {
		Context ctx;
		Connection c = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/connections");
			c = ds.getConnection();
			c.setAutoCommit(true);
		} catch (NamingException | SQLException e) {
			throw new IllegalStateException(e);
		}
		return c;
	}

}