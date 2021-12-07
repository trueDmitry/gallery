package com.epam.learn.java.ad.gallery.api;

import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionRoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.RoomDaoI;
import com.epam.learn.java.ad.gallery.api.db.TransactionDaoI;
import com.epam.learn.java.ad.gallery.api.db.WalletDaoI;
import com.epam.learn.java.ad.gallery.app.db.Selector;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;

public interface DatabaseServiceI {
	
	void startTransaction() throws DBProblemException;
	
	void endTransaction() throws DBProblemException;
	
	ExpositionDaoI getExpositionDao();

	ExpositionOrderDaoI getExpositionOrder();

	WalletDaoI getWalletDao();

	TransactionDaoI getTransactionDao();

	RoomDaoI getRoomDao();

	ExpositionRoomDaoI getExpositionRoomDao();

	Selector getSelector();

	Selector getTransactionSelector();

}
