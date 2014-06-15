package com.wondertrip.model;

public class Ticket {
	private int id;
	private String name;
	private String standPrice;
	private int isChecked;
	private int num;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandPrice() {
		return standPrice;
	}
	public void setStandPrice(String standPrice) {
		this.standPrice = standPrice;
	}
	public int isChecked() {
		return isChecked;
	}
	public void setChecked(int isChecked) {
		this.isChecked = isChecked;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
