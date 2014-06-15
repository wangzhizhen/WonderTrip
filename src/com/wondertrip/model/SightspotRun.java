package com.wondertrip.model;

public class SightspotRun {
	private int id;
	private String ticketIds;
	private int canSaleCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicketIds() {
		return ticketIds;
	}
	public void setTicketIds(String ticketIds) {
		this.ticketIds = ticketIds;
	}
	public int getCanSaleCount() {
		return canSaleCount;
	}
	public void setCanSaleCount(int canSaleCount) {
		this.canSaleCount = canSaleCount;
	}
}
