package com.pilot.mighty.model;

public class UserInfo {
	
	private int no;
	private String userId;
	private String password;
	private String userName;
	private String eMail;
	private String phone;
	private String depart;
	private String userGroup;
	private String langType;
	private String use;
	
	public UserInfo() {}	
	
	public UserInfo(int no, String userId, String password, String userName, String eMail, String phone, String depart,
			String userGroup, String langType, String use) {
		super();
		this.no = no;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.eMail = eMail;
		this.phone = phone;
		this.depart = depart;
		this.userGroup = userGroup;
		this.langType = langType;
		this.use = use;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}
	
}
