package com.wondertrip.model;

public class HotelRoomRun {
	private int id;
	private int active;
	private float stand_price;
	private float set_price;
	private int total_count;
	private String active_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public float getStand_price() {
		return stand_price;
	}
	public void setStand_price(float stand_price) {
		this.stand_price = stand_price;
	}
	public float getSet_price() {
		return set_price;
	}
	public void setSet_price(float set_price) {
		this.set_price = set_price;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public String getActive_date() {
		return active_date;
	}
	public void setActive_date(String active_date) {
		this.active_date = active_date;
	}
	
}
