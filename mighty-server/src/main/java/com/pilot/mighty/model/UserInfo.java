package com.pilot.mighty.model;

public class UserInfo {
	
	private int no;
	private String userId;
	private String userName;
	private String langType;
	private String depart;
	private String userGroup;
	
	public UserInfo() {}	
	
	public UserInfo(int no, String userId, String userName, String langType, String depart, String userGroup) {
		super();
		this.no = no;
		this.userId = userId;
		this.userName = userName;
		this.langType = langType;
		this.depart = depart;
		this.userGroup = userGroup;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
}
