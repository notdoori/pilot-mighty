package com.pilot.mighty.model;

public class UserInfo {
	
	private int no;
	private String userId;
	private String userName;
	private String email;
	private String phone;
	private String depart;
	private String userGroup;
	private String userGroupDesc;
	private String langType;
	private String use;
	
	public UserInfo() {}	
	
	public UserInfo(int no, String userId, String userName, String email, String phone, String depart,
			String userGroup, String userGroupDesc, String langType, String use) {
		super();
		this.no = no;
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.depart = depart;
		this.userGroup = userGroup;
		this.userGroupDesc = userGroupDesc;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUserGroupDesc() {
		return userGroupDesc;
	}

	public void setUserGroupDesc(String userGroupDesc) {
		this.userGroupDesc = userGroupDesc;
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
