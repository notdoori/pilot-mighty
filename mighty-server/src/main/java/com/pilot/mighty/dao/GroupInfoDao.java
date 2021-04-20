package com.pilot.mighty.dao;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.AuthInfo;
import com.pilot.mighty.model.GroupInfo;

public interface GroupInfoDao {
	
	// 모든 사용자 그룹 조회 요청
	GroupInfo[] selectGroupInfoAll();
	
	// 모든 권한 그룹 ID 조회 요청
	AuthInfo[] selectAuthInfoAll();
	
	// 사용자 그룹 정보 조회 요청
	HashMap<String, Object> selectGroupInfoSearch(Map<String, Object> map);
	
	// 사용자 그룹 정보 추가 요청
	void insertGroupInfo(Map<String, String> map);
	
	// 사용자 그룹 정보 수정 요청
	void updateGroupInfo(Map<String, String> map);
	
	// 사용자 그룹 정보 삭제 요청
	void deleteGroupInfo(Map<String, String> map);
	
	// 특정 사용자 그룹 리스트 조회
	HashMap<String, Object> selectGroupInfoCheck(Map<String, Object> map);
	
	// (Swagger 프로그램) 사용자 그룹 정보 추가 요청
	void insertGroupInfoSwagger(String groupId, String groupDesc, String roleId);
	
	// (Swagger 프로그램) 사용자 그룹 정보 수정 요청
	void updateGroupInfoSwagger(String groupId, String groupDesc, String roleId);
	
	// (Swagger 프로그램) 사용자 그룹 정보 삭제 요청
	void deleteGroupInfoSwagger(String groupId);
}
