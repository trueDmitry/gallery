package com.epam.learn.java.ad.gallery.app;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.PaymentServiceI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionDaoI;
import com.epam.learn.java.ad.gallery.api.db.ExpositionOrderDaoI;
import com.epam.learn.java.ad.gallery.api.db.TransactionDaoI;
import com.epam.learn.java.ad.gallery.api.db.WalletDaoI;
import com.epam.learn.java.ad.gallery.app.exception.DBProblemException;
import com.epam.learn.java.ad.gallery.app.exception.LowBalanceException;
import com.epam.learn.java.ad.gallery.app.exception.NoWalletException;
import com.epam.learn.java.ad.gallery.app.exception.TickectAlreadyBoughtException;
import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.ExpositionTicket;
import com.epam.learn.java.ad.gallery.app.model.Transaction;
import com.epam.learn.java.ad.gallery.app.model.User;
import com.epam.learn.java.ad.gallery.app.model.Wallet;

public class PaymentService implements PaymentServiceI {

	private ApplicationContext context;
	private DatabaseServiceI databaseService;

	public PaymentService(ApplicationContext context, DatabaseServiceI databaseService) {
		this.context = context;
		this.databaseService = databaseService;
	}

	/**
	 * current user orders Exposition
	 * 
	 * @param expoId
	 * @throws NoWalletException
	 * @throws LowBalanceException
	 * @throws DBProblemException
	 * @throws TickectAlreadyBoughtException
	 */
	public void buyExpositionTicket(int expoId)
			throws LowBalanceException, NoWalletException, DBProblemException, TickectAlreadyBoughtException {
		User user = context.getUser();
		try (ExpositionDaoI edao = databaseService.getExpositionDao();
				ExpositionOrderDaoI orderTable = databaseService.getExpositionOrder();
				WalletDaoI walletDao = databaseService.getWalletDao();
				TransactionDaoI transDao = databaseService.getTransactionDao()) {
			databaseService.startTransaction();

			Optional<Exposition> expoOption = edao.get(expoId);
			expoOption.orElseThrow(() -> new NoSuchElementException("Exposition id " + expoId + " not found"));

			Optional<ExpositionTicket> orderOption = orderTable.find(expoOption.get().getId(), user.getId());
			if (orderOption.isPresent()) {
				throw new TickectAlreadyBoughtException();
			}

			ExpositionTicket order = createExpositionOrder(expoOption.get());

			Wallet wallet = walletDao.getByUser(user.getId());

			checkBalance(wallet, order);

			Transaction transaction = createTransaction(order, wallet);
			transDao.insert(transaction);

			walletDao.changeBalanceBy(transaction.getAmount());

			order.setTransactionId(transaction.getId());
			orderTable.insert(order);

			databaseService.endTransaction();
		}

	}

	private Transaction createTransaction(ExpositionTicket order, Wallet wallet) {
		Transaction t = new Transaction();
		t.setActorId(order.getUserId());
		t.setWalletId(wallet.getId());
		t.setAmount(order.getPrice());
		t.setCreateDate(new Date());
		return t;
	}

	private void checkBalance(Wallet wallet, ExpositionTicket order) throws LowBalanceException {
		if (wallet.getBalance() < order.getPrice()) {
			throw new LowBalanceException();
		}
	}

	private ExpositionTicket createExpositionOrder(Exposition expo) {
		ExpositionTicket eo = new ExpositionTicket();
		eo.setExpositionId(expo.getId());
		eo.setPrice(expo.getPrice());
		eo.setUserId(context.getUser().getId());
		eo.setCreateDate(new Date());
		return eo;
	}

}
