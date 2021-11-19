package com.epam.learn.java.ad.gallery.model;

import java.util.Date;

public class Transaction {
	private int id;
	private int walletId;
	private int actorId;
	private int amount;
	private Date createDate; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public int getActor_id() {
		return actorId;
	}

	public void setActorId(int actor_id) {
		this.actorId = actor_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
}
