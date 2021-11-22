package com.epam.learn.java.ad.gallery.app.db;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.epam.learn.java.ad.gallery.app.model.User;

public class UserDao {
	
	public static String getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		return DigestUtils.md5Hex(password).toUpperCase();
	}

	public static User getUser(String login, String password) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String passwordHash = getHash(password);
		User user = null;
		String sql = "SELECT id, email, fullname, login " + "FROM user " + "WHERE login = ? and password = ?";
		try (Connection con = ConnectionPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, login);
			ps.setString(2, passwordHash);
			ps.execute();

			try (ResultSet rs = ps.getResultSet()) {
				if (rs.next()) {
					user = new User();
					user.setId(rs.getInt(1));
					user.setEmail(rs.getString(2));
					user.setFullName(rs.getString(3));
					user.setLogin(rs.getString(4));
					setRoles(con, user);
				}
			}
		}
		return user;
	}

	private static void setRoles(Connection con, User user) throws SQLException {
		String sql = "SELECT name FROM user_role JOIN role ON role_id = id WHERE user_id = " + user.getId();
		
		List<String> roles = new ArrayList<>(); 
		try(Statement s = con.createStatement(); ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				roles.add(rs.getString(1));
			}
		}
		user.setRoles(roles);
	}


}
