package com.epam.learn.java.ad.gallery.api.db;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.exception.NoWalletException;
import com.epam.learn.java.ad.gallery.app.model.Wallet;

public interface WalletDaoI extends BaseDaoI<Wallet> {

	Wallet getByUser(int id) throws NoWalletException, DBProblemException;

	void changeBalanceBy(int amount) throws DBProblemException;

}
