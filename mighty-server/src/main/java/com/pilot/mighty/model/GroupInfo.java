package com.pilot.mighty.model;

public class GroupInfo {
	
	private String userId;
	private String userName;
	private String langType;
	private String depart;
	
	public GroupInfo() {}	
	
	public GroupInfo(String userId, String userName, String langType, String depart) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.langType = langType;
		this.depart = depart;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getLangType() {
		return langType;
	}
	
	public void setLangType(String langType) {
		this.langType = langType;
	}
	
	public String getDepart() {
		return depart;
	}
	
	public void setDepart(String depart) {
		this.depart = depart;
	}
}
