package com.epam.learn.java.ad.gallery.app.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.db.TransactionDaoI;
import com.epam.learn.java.ad.gallery.model.Transaction;

public class TransactionDao extends BaseDao<Transaction> implements TransactionDaoI {

	public TransactionDao(Connection con) {
		super(con);
	}

	@Override
	protected void updateKey(Transaction object, int i) {
		object.setId(i);
	}

	@Override
	protected void putObject(PreparedStatement ps, Transaction o) throws SQLException {
		ps.setInt(1, o.getWalletId());
		ps.setInt(2, o.getActor_id());
		ps.setInt(3, o.getAmount());
		ps.setDate(4, new Date(o.getCreateDate().getTime()));

	}

	@Override
	protected String insertSQL() {
		return "INSERT INTO  transaction (wallet_id, actor_id, amount, timestamp) VALUES (?, ?, ?, ?)";
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
	protected Optional<Transaction> getObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
