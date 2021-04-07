package com.pilot.mighty.model;

public class AuthInfo {
	
	private String roleId;
	private String roleDesc;
	
	public AuthInfo() {}	
	
	public AuthInfo(String roleId, String roleDesc) {
		super();
		this.roleId = roleId;			// 권한 그룹 아이디
		this.roleDesc = roleDesc;		// 권한 그룹 설명
	}

	public String getAuthId() {
		return roleId;
	}
	
	public void setAuthId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getAuthDesc() {
		return roleDesc;
	}
	
	public void setAuthDesc(String authDesc) {
		this.roleDesc = authDesc;
	}
}
