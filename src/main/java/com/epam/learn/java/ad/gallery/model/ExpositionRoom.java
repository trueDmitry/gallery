package com.epam.learn.java.ad.gallery.model;

public class ExpositionRoom {
	int expositionId;
	
	int roomId;

	public ExpositionRoom() {
	}
	
	public ExpositionRoom(int expositionId, int roomId) {
		setExpositionId( expositionId);
		setRoomId(roomId);
	}

	public int getExpositionId() {
		return expositionId;
	}

	public void setExpositionId(int expositionId) {
		this.expositionId = expositionId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}
