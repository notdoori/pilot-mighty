package com.pilot.mighty.service;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.GroupInfo;

public interface GroupService {
	// 모든 사용자 그룹 리스트 조회
	GroupInfo[] selectGroupInfoAll();
	
	// 특정 사용자 그룹 리스트 조회
	HashMap<String, Object> selectGroupInfo(Map<String, Object> map);
	
	// 사용자 그룹 리스트 추가
	void insertGroupInfo(Map<String, String> map);
	
	// 특정 사용자 그룹 리스트 설명 업데이트
	void updateGroupInfo(Map<String, String> map);
	
	// 특정 사용자 권한 그룹 리스트 삭제
	void deleteGroupInfo(Map<String, String> map);
}
