package com.pilot.mighty.model;

public class AuthInfo {
	
	private int no;
	private String roleId;
	private String roleDesc;
	
	public AuthInfo() {}
	
	public AuthInfo(int no, String roleId, String roleDesc) {
		super();
		this.no = no;						// 카운트 번호
		this.roleId = roleId;				// 권한 그룹 아이디
		this.roleDesc = roleDesc;		// 권한 그룹 설명
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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
