package com.pilot.mighty.model;

public class GroupInfo {
	
	private String groupId;
	private String groupDesc;
	private String roleId;
	
	public GroupInfo() {}	
	
	public GroupInfo(String groupId, String groupDesc, String roleId) {
		super();
		this.groupId = groupId;			// 사용자 그룹 아이디
		this.groupDesc = groupDesc;		// 사용자 그룹 설명
		this.roleId = roleId;			// 권한 그룹 아이디
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
