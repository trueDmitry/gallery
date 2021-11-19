package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.model.ExpositionTicket;

public class ExpositionOrderDao extends BaseDao<ExpositionTicket> implements ExpositionOrderDaoI{

	public ExpositionOrderDao(Connection con) {
		super(con);
	}

	@Override
	protected void updateKey(ExpositionTicket object, int i) {
		object.setId(i);
	}

	@Override
	protected void putObject(PreparedStatement ps, ExpositionTicket o) throws SQLException {
		ps.setInt(1, o.getExpositionId());
		ps.setInt(2, o.getUserId());
		ps.setInt(3, o.getPrice());
		ps.setDate(4, new Date(o.getCreateDate().getTime()));
		ps.setInt(5, o.getTransactionId());
	}

	@Override
	protected String insertSQL() {
		return "INSERT INTO exposition_order (exposition_id, user_id, price, timestamp, transaction_id) VALUES (?, ?, ?, ?, ?)";
	}

	@Override
	protected String updateSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String selectByIdSQL(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Optional<ExpositionTicket> getObject(ResultSet rs) throws SQLException {
		ExpositionTicket order = new ExpositionTicket();
		order.setId(rs.getInt(1));
		order.setExpositionId(rs.getInt(2));
		order.setUserId(rs.getInt(3));
		order.setPrice(rs.getInt(4));
		order.setCreateDate(rs.getDate(5));
		order.setTransactionId(rs.getInt(6));
		return Optional.of(order);
	}

	public Optional<ExpositionTicket> find(int expoId, int userId) throws DBProblemException {
		String sql = "SELECT id, exposition_id, user_id, price, timestamp, transaction_id FROM exposition_order "
				+ "WHERE exposition_id = " + expoId + " AND " + " user_id = " + userId;
		try(Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			if (rs.next()) {
				return getObject(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBProblemException();
		}
		return Optional.empty();
	}

	public List<ExpositionTicket> selectForUser(int userId) throws DBProblemException {
		List<ExpositionTicket> list = new ArrayList<>();
		String sql = "SELECT id, exposition_id, user_id, price, timestamp, transaction_id FROM exposition_order "
				+ "WHERE  user_id = " + userId;
		try(Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(getObject(rs).get());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBProblemException();
		}
		return list;
	}

}
