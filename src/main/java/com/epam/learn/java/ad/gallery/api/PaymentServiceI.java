package com.epam.learn.java.ad.gallery.api;

import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.exception.LowBalanceException;
import com.epam.learn.java.ad.gallery.app.exception.NoWalletException;
import com.epam.learn.java.ad.gallery.app.exception.TickectAlreadyBoughtException;

public interface PaymentServiceI {

	void buyExpositionTicket(int expoId) throws LowBalanceException, NoWalletException, DBProblemException, TickectAlreadyBoughtException;

}
