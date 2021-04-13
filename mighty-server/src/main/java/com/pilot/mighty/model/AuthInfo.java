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

	public String getRoleId() {
		return roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleDesc() {
		return roleDesc;
	}
	
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
