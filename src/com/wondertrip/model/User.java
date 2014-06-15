package com.wondertrip.model;

public class User {
	private int id;
	private String username;
	private String educatedLevel;
	private String salaryLevel;
	private String gender;
	public static User currUser=null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEducatedLevel() {
		return educatedLevel;
	}
	public void setEducatedLevel(String educatedLevel) {
		this.educatedLevel = educatedLevel;
	}
	public String getSalaryLevel() {
		return salaryLevel;
	}
	public void setSalaryLevel(String salaryLevel) {
		this.salaryLevel = salaryLevel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public User getCurrProfile(){
		return currUser;
	}
	public static void setCurrProfile(User user){
		currUser = new User();
		currUser.setEducatedLevel(user.getEducatedLevel());
		currUser.setGender(user.getGender());
		currUser.setId(user.getId());
		currUser.setSalaryLevel(user.getSalaryLevel());
		currUser.setUsername(user.getUsername());
	}
}
