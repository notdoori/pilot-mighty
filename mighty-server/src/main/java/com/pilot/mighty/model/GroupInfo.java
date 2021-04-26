package com.pilot.mighty.model;

public class GroupInfo {
	
	private int no;
	private String groupId;
	private String groupDesc;
	private String roleId;
	
	public GroupInfo() {}	
	
	public GroupInfo(int no, String groupId, String groupDesc, String roleId) {
		super();
		this.no = no;						// 카운트 번호
		this.groupId = groupId;				// 사용자 그룹 아이디
		this.groupDesc = groupDesc;		// 사용자 그룹 설명
		this.roleId = roleId;					// 권한 그룹 아이디
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getGroupDesc() {
		return groupDesc;
	}
	
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	
	public String getRoleId() {
		return roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
