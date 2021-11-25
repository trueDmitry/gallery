package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.db.WalletDaoI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.exception.NoWalletException;
import com.epam.learn.java.ad.gallery.app.model.Wallet;

public class WalletDao extends BaseDao<Wallet> implements WalletDaoI {

	public WalletDao(Connection con) {
		super(con);
	}

	@Override
	protected String selectByIdSQL(int id) {
		return "SELECT id, user_id, balance FROM wallet WHERE id = " + id;
	}

	@Override
	protected Wallet getObject(ResultSet rs) throws SQLException {
		Wallet w = new Wallet();
		w.setId(rs.getInt(1));
		w.setUserId(rs.getInt(2));
		w.setBalance(rs.getInt(3));
		return w;
	}

	@Override
	protected void updateKey(Wallet object, int i) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void putObject(PreparedStatement ps, Wallet o) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected String insertSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String updateSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Does not return null
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws NoWalletException if no wallet found
	 * @throws DBProblemException 
	 */
	public Wallet getByUser(int userId) throws NoWalletException, DBProblemException {
		String sql = "SELECT id, user_id, balance FROM wallet WHERE user_id = " + userId;
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			if (rs.next()) {
				return getObject(rs);
			}
			throw new NoWalletException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBProblemException();
		}
	}

	public void changeBalanceBy(int amount) throws DBProblemException  {
		String sql = "UPDATE wallet SET balance = balance - " + amount;
		try (Statement st = con.createStatement()) {
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBProblemException();
		}
	}

}
