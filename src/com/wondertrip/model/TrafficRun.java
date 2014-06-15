package com.wondertrip.model;

public class TrafficRun {
	private int runId;
	private int canSaleCount;
	private String ticketIds;
	private String startTime;
	private String goContent;
	private String backContent;
	public int getRunId() {
		return runId;
	}
	public void setRunId(int runId) {
		this.runId = runId;
	}
	public int getCanSaleCount() {
		return canSaleCount;
	}
	public void setCanSaleCount(int canSaleCount) {
		this.canSaleCount = canSaleCount;
	}
	public String getTicketIds() {
		return ticketIds;
	}
	public void setTicketIds(String ticketIds) {
		this.ticketIds = ticketIds;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getGoContent() {
		return goContent;
	}
	public void setGoContent(String goContent) {
		this.goContent = goContent;
	}
	public String getBackContent() {
		return backContent;
	}
	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}
	public String toString(){
		return startTime+":"+goContent+","+backContent;
	}
}
