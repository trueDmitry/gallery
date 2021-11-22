package com.epam.learn.java.ad.gallery.app.model;

import java.util.Date;

public class ExpositionTicket {
	private int id;
	private int expositionId;
	private int userId;
	private int price;
	private Date createDate; 
	private int transactionId;
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExpositionId() {
		return expositionId;
	}
	public void setExpositionId(int expositionId) {
		this.expositionId = expositionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
