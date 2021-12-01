package com.epam.learn.java.ad.gallery.app;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionRoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.RoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.TransactionDaoI;
import com.epam.learn.java.ad.gallery.api.db.WalletDaoI;
import com.epam.learn.java.ad.gallery.app.db.ConnectionPool;
import com.epam.learn.java.ad.gallery.app.db.ExpositionDao;
import com.epam.learn.java.ad.gallery.app.db.ExpositionOrderDao;
import com.epam.learn.java.ad.gallery.app.db.ExpositionRoomDao;
import com.epam.learn.java.ad.gallery.app.db.RoomDao;
import com.epam.learn.java.ad.gallery.app.db.TransactionDao;
import com.epam.learn.java.ad.gallery.app.db.WalletDao;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;

/**
 * new instance per request
 * 
 * @author Administrator
 *
 */
public class MysqlService implements DatabaseServiceI {

	Connection con = null;

	@Override
	public ExpositionDaoI getExpositionDao() {
		return new ExpositionDao(getConnection());
	}

	private Connection getConnection() {
		return con != null ? con : acquireConnection();
	}

	private Connection acquireConnection() {
		return ConnectionPool.getConnection();
	}

	@Override
	public void startTransaction() throws DBProblemException {
		if (con == null) {
			con = acquireConnection();
		}
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DBProblemException(e);
		}
	}

	@Override
	public void endTransaction() throws DBProblemException {
		if (con != null) {
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				throw new DBProblemException(e);
			}
			con = null;
		}
	}

	@Override
	public ExpositionOrderDaoI getExpositionOrder() {
		return new ExpositionOrderDao(getConnection());
	}

	@Override
	public WalletDaoI getWalletDao() {
		return new WalletDao(getConnection());
	}

	@Override
	public TransactionDaoI getTransactionDao() {
		return new TransactionDao(getConnection());
	}

	@Override
	public RoomDaoI getRoomDao() {
		return new RoomDao(getConnection());
	}

	@Override
	public ExpositionRoomDaoI getExpositionRoomDao() {
		return new ExpositionRoomDao(getConnection());
	}

}
