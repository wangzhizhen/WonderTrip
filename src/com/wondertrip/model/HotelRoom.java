package com.wondertrip.model;

public class HotelRoom {
	private int roomId;
	private String roomType;
	private String standPrice;
	private String promotePrice;
	private String breakfast;
	private String bedType;
	private String internet;
	private boolean available;
	private String square;
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getStandPrice() {
		return standPrice;
	}
	public void setStandPrice(String standPrice) {
		this.standPrice = standPrice;
	}
	public String getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(String promotePrice) {
		this.promotePrice = promotePrice;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	
}
