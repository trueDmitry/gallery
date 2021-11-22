package com.epam.learn.java.ad.gallery.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Exposition {
	
	@Override
	public String toString() {
		return "Exposition [id=" + id + ", theme=" + theme + ", price=" + price + ", start=" + start + ", end=" + end
				+ ", open=" + open + ", close=" + close + ", Published=" + published + ", rooms=" + rooms + "]";
	}

	private int id;
	private String theme;
	private int price;
	private Date start;
	private Date end;
	
	private int open;
	private int close;
	private boolean published;
	
	private List<Room> rooms;
	
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getClose() {
		return close;
	}
	public void setClose(int close) {
		this.close = close;
	}
	
	public boolean getPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Room> getRooms() {
		if (rooms == null) {
			rooms = new ArrayList<Room>();
		}
		return rooms;
	}
	public void addRoom(Room room) {
		getRooms().add(room);
	}
	
}
